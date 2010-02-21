package com.vms.action.vediotape;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.CommonVariable;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Company;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.*;

public class VediotapeMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(VediotapeMgmtAction.class);
	private IVediotapeService vedioService;
	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;
	private IAudienceScoreService audienceScoreService;
	private String vname;
	private String vid;
	private String optionName;

	private Vediotape vedio;
	private VedioTapeVO vv;
	private String jasonDataString;
	
	
	public String toAddingVedio() throws Exception {		
		return SUCCESS;
	}

	public String doAddingVedio() throws Exception {
		List<Vediotape> vedios = this.convertJASSONStringToVedio();
		try{
			vedioService.createVediotapes(vedios);	
		}catch(Exception e){
			logger.error(e);
			this.addActionError("添加失败");
			return INPUT;
		}
		this.addActionMessage("添加成功");
		return this.SUCCESS;
	}

	
	private List<Vediotape> convertJASSONStringToVedio() throws ParseException{
		JSONArray jasonArray = JSONArray.fromObject(this.jasonDataString);
		DateFormat format = new SimpleDateFormat();
		
		if(jasonArray.isArray() && !jasonArray.isEmpty()){
			List<Vediotape> vedios = new ArrayList<Vediotape>();
			SessionUserInfo userInfo = (SessionUserInfo)session.getAttribute("SessionUserInfo");
			Date date =new Date();
			int size = jasonArray.size();
			for (int i = 0; i < size; i++) {
				JSONObject obj =jasonArray.getJSONObject(i);
				Vediotape v =new Vediotape();
				v.setId(obj.getString(Vediotape.PROP_ID));
				v.setVedioName(obj.getString(Vediotape.PROP_VEDIO_NAME));
				v.setCompanyID(new Company(obj.getInt(Vediotape.PROP_COMPANY_ID)));
				v.setTopic(new Topic(obj.getInt(Vediotape.PROP_TOPIC)));
				v.setSubject(new Subject(obj.getInt(Vediotape.PROP_SUBJECT)));
				v.setStatus(new Status(1));
				v.setDateComing(format.parse(obj.getString(Vediotape.PROP_DATE_COMING)));
				v.setComments(obj.getString(Vediotape.PROP_COMMENTS));
				//v.setInputer(new User(userInfo.getUserId()));
				v.setInputer(new User(1));				
				v.setDateInput(date);
				vedios.add(v);
			}
			return vedios;
		}		
		return null;
	}
	
	public String toMarketRate()throws Exception{
		return SUCCESS;
	}
	
	public String toUpdateVideoInfo() throws Exception{
		return this.SUCCESS;
	}
	
	public String updateMarketRate()throws Exception{
		vedioService.updateVideoRatingMarket(vedio.getId(), vedio.getMarketShare(), vedio.getAudienceRating());
		return SUCCESS;
	}
	
	
	public String searchVideoByNameOrID()throws Exception{
		
		List<Vediotape> list = null;
		try {
			if(!"".equals(vid)){
				list = vedioService.findVediotapeByProperty(Vediotape.PROP_ID, vid, -1, -1, "", false);
			}else{
				list  = vedioService.findVediotapeByProperty(Vediotape.PROP_VEDIO_NAME, vname, -1, -1, "", false);
			}	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}		
		if(list!=null && !list.isEmpty()){
			this.vedio = list.get(0);
			int status = vedio.getStatus().getId();
			String videoid = vedio.getId();
			if(null != optionName && !"".equals(optionName)){
				if(optionName.equals("auditing")){
					if(status == CommonVariable.VIDEO_STATUS_AUDITING || status ==CommonVariable.VIDEO_STATUS_REAUDITING ){
						List audienceVote = this.audienceScoreService.getAudienceScoreOfTape(videoid, -1, -1, "", true);
						vv = this.vedioService.getVideotapeById(videoid, audienceVote);
						return SUCCESS;
					}else if(status == CommonVariable.VIDEO_STATUS_MODIFICATION) {
						this.addActionError("影带状态正在修改中，不能审核");
						return INPUT;
					}else{
						this.addActionError("影带状态为"+vedio.getStatus().getStatus()+"，不能审核");
						return INPUT;
					}					
				}else if(optionName.equals("modification")){					
					if(status == CommonVariable.VIDEO_STATUS_MODIFICATION){
						List audienceVote = this.audienceScoreService.getAudienceScoreOfTape(videoid, -1, -1, "", true);
						vv = this.vedioService.getVideotapeById(videoid, audienceVote);
						return SUCCESS;
					}else{
						this.addActionError("影带状态为"+vedio.getStatus().getStatus()+"，不能操作");
						return INPUT;
					}					
				}else if(optionName.equals("marketRate")){
					if(status == CommonVariable.VIDEO_STATUS_PLAYED){
						List audienceVote = this.audienceScoreService.getAudienceScoreOfTape(videoid, -1, -1, "", true);
						vv = this.vedioService.getVideotapeById(videoid, audienceVote);
						return SUCCESS;
					}else{
						this.addActionError("影带状态为"+vedio.getStatus().getStatus()+"，不能操作");
						return INPUT;
					}
					
					
				}
			}
			return this.SUCCESS;
		}
		this.addActionError("影带未找到");
		return this.INPUT;
	}
	
	public String updateVideoInfo() throws Exception {
		try {
			this.vedioService.updateVideoInfo(vedio);
			this.addActionMessage("更新成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("更新失败");
		return this.INPUT;
	}
	
	
	public String modificationFinish()throws Exception{
		try {
			SessionUserInfo user = new SessionUserInfo(1);
			this.vedioService.auditingVideo(vv.getVedioID(), user, 5);
			this.addActionMessage("影带已进入重审状态,等待审核");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("修改影带状态失败");
		return INPUT;
		
	}
	
	public String toVideoModifications()throws Exception{
		return SUCCESS;
		
	}
	
	
	
	public String isVediotapeExsits() throws Exception {
		String vedioName = this.getRequest().getParameter("vedioName");
		String x = java.net.URLDecoder.decode(vedioName, "UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		vedio = this.vedioService.getVediotapeByName(x);
		
		StringBuffer sb =new StringBuffer();
		if(vedio!=null){
			sb.append("影带已存在，请检查影带名称");
		}else{
			sb.append("SUCCESS");
		}		
		out.println(sb.toString());
		out.close();
		return NONE;
	}

	
	
	public List<Company> getComList() throws Exception{
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true);		
	}


	public List<Topic> getTopList() throws Exception{
		return topicService.findAllTopics(-1,-1, Topic.PROP_ID, true);
	}
	
	public List<Subject> getSubList() throws Exception{
		return subjectService.findAllSubjects(-1, -1, Subject.PROP_ID, true);
	}
	
	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public IVediotapeService getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVediotapeService vedioService) {
		this.vedioService = vedioService;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public Vediotape getVedio() {
		return vedio;
	}

	public void setVedio(Vediotape vedio) {
		this.vedio = vedio;
	}

	public String getJasonDataString() {
		return jasonDataString;
	}

	public void setJasonDataString(String jasonDataString) {
		this.jasonDataString = jasonDataString;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public IAudienceScoreService getAudienceScoreService() {
		return audienceScoreService;
	}

	public void setAudienceScoreService(IAudienceScoreService audienceScoreService) {
		this.audienceScoreService = audienceScoreService;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public VedioTapeVO getVv() {
		return vv;
	}

	public void setVv(VedioTapeVO vv) {
		this.vv = vv;
	}

	

}
