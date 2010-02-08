package com.vms.action.vediotape;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.vms.common.BaseAction;
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
	private IVediotapeService vedioService;
	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;

	private Vediotape vedio;
	private String jasonDataString;
	public String toAddingVedio() throws Exception {
		
		
		return SUCCESS;
	}

	public String doAddingVedio() throws Exception {
		List<Vediotape> vedios = this.convertJASSONStringToVedio();
		vedioService.createVediotapes(vedios);
		return this.SUCCESS;
	}

	
	
	private List<Vediotape> convertJASSONStringToVedio(){
		JSONArray jasonArray = JSONArray.fromObject(this.jasonDataString);
		if(jasonArray.isArray() && !jasonArray.isEmpty()){
			List<Vediotape> vedios = new ArrayList<Vediotape>();
			SessionUserInfo userInfo = (SessionUserInfo)session.getAttribute("SessionUserInfo");
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
				//v.setInputer(new User(userInfo.getId()));
				vedios.add(v);
			}
			return vedios;
		}		
		return null;
	}
	public List<Company> getComList() throws Exception{
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true);		
	}


	public List<Topic> getTopList() throws Exception{
		return topicService.findAllTopics(-1,-1, Topic.PROP_ID, true);
	}
	
	public List<Subject> getSubList() throws Exception{
		return subjectService.findAllSubjects(-1, 01, Subject.PROP_ID, true);
	}
	
	
	
	public String getCompaniesStr() throws Exception {
		StringBuffer sb =new StringBuffer("[");
		List<Company>  coms = companyService.findAllCompany(-1, -1, Company.PROP_ID, true);
		int size =coms.size();
		for (int i=0;i<size;i++) {
			Company c = coms.get(i);
			if(i!=0 && i<size ){
				sb.append(",");
			}
			sb.append("{\"id\":\""+c.getId()+"\",\"companyName\":\""+c.getCompanyName()+"\"}");
			
		}
		sb.append("]");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
		return NONE;
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

	

}
