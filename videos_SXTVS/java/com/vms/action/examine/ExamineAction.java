package com.vms.action.examine;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.CommonVariable;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IUserService;
import com.vms.service.iface.IVedioscoreService;
import com.vms.service.iface.IVediotapeService;

public class ExamineAction extends BaseAction {
	
	private static final long serialVersionUID = -4049982603104035005L;
	
	private static Logger logger = Logger.getLogger(ExamineAction.class);
	
	private IVedioscoreService vedioscoreService;
	
	private IVediotapeService vediotapeService;
	
	private IUserService userService;

	private JSONDataTable unExaminedTable;
	
	private JSONDataTable examinedTable;
	
	private VedioScoreVO tapeScore;
	
	private String vid;
	
	private String vname;
	
	private String uid;
	
	private String perform;
	
	private String errorMsg;
	
	private List<User> examiners;
	
	private String examinerByInputer;
	
	private String newResult;

	private int type;
	
	public String toUnExaminedTapes() {
		return SUCCESS;
	}
	
	public String toExaminedTapes() {
		return SUCCESS;
	}
	
	public String getUnExaminedTapes() throws Exception {
		unExaminedTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		List<VedioTapeVO> tapes;
		try {
			if((null==vid || "".equals(vid)) && (null==vname || "".equals(vname))){
				tapes = vedioscoreService.getAllUnExaminedVedioes(unExaminedTable.getStartIndex(), 
						unExaminedTable.getRowsPerPage(),
						unExaminedTable.getSort(),
						unExaminedTable.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
				Status status = new Status(1);
				JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, vedioscoreService.getVedioCountByStatus(status));
			}else{
				List<Vediotape> list;
				//for chinese characters, need to convert to utf-8
				vname = new String(vname.getBytes("iso-8859-1"),"utf-8");
				if(null!=vid && !"".equals(vid)){
					list = vediotapeService.findVediotapeByProperty(Vediotape.PROP_ID, vid, -1, -1, "", false);
				}else{
					list  = vediotapeService.findVediotapeByProperty(Vediotape.PROP_VEDIO_NAME, vname, -1, -1, "", false);
				}
				if(list.size() == 0){
					errorMsg = "影带未找到。";
					this.addActionError(errorMsg);
					return INPUT;
				}else{
					if(list.get(0).getStatus().getId() != CommonVariable.VIDEO_STATUS_EXAMINE){
						errorMsg = "影带状态为" + list.get(0).getStatus().getStatus() + ",不能打分。";
						this.addActionError(errorMsg);
						return INPUT;
					}
				}
				tapes = vedioscoreService.setTapeExamineInfo(list);
				JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, 1);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return SUCCESS;
	}
	
	public String getExaminedTapes() throws Exception {
		examinedTable = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<VedioScoreVO> scores;
			if(null!=vid && !"".equals(vid)){
				scores = vedioscoreService.findUserExamineScoreByVideoId(
						vid, 
						examinedTable.getStartIndex(), 
						examinedTable.getRowsPerPage(),
						examinedTable.getSort(), examinedTable.getDir().equals(JSONDataTableUtils.SORT_DIRECTION)
						);
			}else{
				//for chinese characters, need to convert to utf-8
				vname = new String(vname.getBytes("iso-8859-1"),"utf-8");
				Vediotape tape = vediotapeService.getVediotapeByName(vname);
				if(null != tape){
					vid = tape.getId();
					scores = vedioscoreService.findUserExamineScoreByVideoId(
						tape.getId(), 
						examinedTable.getStartIndex(), 
						examinedTable.getRowsPerPage(),
						examinedTable.getSort(), examinedTable.getDir().equals(JSONDataTableUtils.SORT_DIRECTION)
						);
				}else{
					scores = new ArrayList<VedioScoreVO>();
				}
			}
			if(scores.size() == 0){
				errorMsg = "该影带没有评分或找不到该影带。";
				this.addActionError(errorMsg);
				return INPUT;
			}
			JSONDataTableUtils.setupJSONDataTable(scores, examinedTable, vedioscoreService.getTotalCountUserExamineScoreByVideoId(vid));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return SUCCESS;
	}
	
	public String toExamineTape() throws Exception{	
		if(null == tapeScore.getVedioID() || "".equals(tapeScore.getVedioID())) return INPUT;
		//for chinese characters, need to convert to utf-8
		//String vname = new String(tape.getVedioName().getBytes("iso-8859-1"),"utf-8");
		type = vediotapeService.getVediotapeByID(tapeScore.getVedioID()).getSubject().getId();
		if("modify".equals(perform)){
			tapeScore = vedioscoreService.getTapeScoreByIdAndUser(tapeScore.getVedioID(), Integer.parseInt(uid));
		}else{
			//has user examined, if examined, page readonly
			int examiner;
			if(null != uid && !"".equals(uid)){
				examiner = Integer.parseInt(uid);
			}else{
				examiner = this.getUserInfo().getUserId();
			}
			VedioScoreVO temp = vedioscoreService.getTapeScoreByIdAndUser(tapeScore.getVedioID(), examiner);
			if(null != temp && !this.getUserInfo().getRoles().contains(8)){
				tapeScore = temp;
				return "view";
			}
			
			String name = vedioscoreService.getTapeByID(tapeScore.getVedioID()).getVedioName();		
			tapeScore.setVedioName(name);
		}
		if(this.getUserInfo().getRoles().contains(8)){
			examiners = vedioscoreService.findAllExaminer();
		}
		if(getUserInfo().getRoles().contains(new Integer(8)) && !"modify".equals(perform)){
			vid=tapeScore.getVedioID();
			return "inputerExam";
		}
		return SUCCESS;
	}
	
	public String doExamineTape() throws Exception{	
		int userID;
		if("modify".equals(perform)){
			//moidfy by inputer
			userID = Integer.parseInt(uid);
		}else{
			if(null != examinerByInputer && !"".equals(examinerByInputer)){
			//create by inputer
				User temp = userService.getUserByEmployeeName(examinerByInputer);
				if(null == temp){
					this.addActionError("没有找到打分人员" + examinerByInputer + "。");
					toExamineTape();
					return INPUT;
				}
				userID = temp.getId();
				
				VedioScoreVO score = vedioscoreService.getTapeScoreByIdAndUser(tapeScore.getVedioID(), userID);
				if(null != score){
					this.addActionError("该影带已经被" + examinerByInputer + "打过分。如果要修改打分，请到修改打分页面修改。");
					toExamineTape();
					return INPUT;
				}
			}else{
			//create by examiner
				userID = this.getUserInfo().getUserId();
			}
		}
		tapeScore.setUserID(userID);
		tapeScore.setOperator(this.getUserInfo().getUserId());
		vedioscoreService.saveVedioScore(tapeScore);
		
		if("modify".equals(perform)){
			toExamineTape();
			this.addActionMessage("修改成功。");
			return SUCCESS;
		}
		return "back";
	}
	
	public String doExamineTapeByInputer() throws Exception{
		JSONArray jsonArray = JSONArray.fromObject(newResult);
//		List<VedioScoreVO> scores = new ArrayList<VedioScoreVO>();
		if(jsonArray.isArray() && !jsonArray.isEmpty()){
			int size = jsonArray.size();
			for (int i = 0; i < size; i++) {
				JSONObject obj =jsonArray.getJSONObject(i);
				VedioScoreVO s = new VedioScoreVO();
				s.setVedioID(vid);
				s.setExaminer(obj.getString("examiner"));
				s.setStoryScore((float)obj.getDouble("storyScore"));
				s.setTechScore((float)obj.getDouble("techScore"));
				s.setPerformScore((float)obj.getDouble("performScore"));
				s.setInnovateScore((float)obj.getDouble("innovateScore"));
				s.setOperator(this.getUserInfo().getUserId());
				s.setAward(obj.getInt("award") + "");
				s.setPurchase(obj.getInt("purchase") + "");
				s.setOrientation(obj.getInt("orientation"));
				User temp = userService.getUserByEmployeeName(s.getExaminer());
				s.setUserID(temp.getId());
//				scores.add(s);
				VedioScoreVO score = vedioscoreService.getTapeScoreByIdAndUser(s.getVedioID(), s.getUserID());
				if(null != score){
					this.addActionError("该影带已经被" + s.getExaminer() + "打过分。如果要修改打分，请到修改打分页面修改。");
					tapeScore = vedioscoreService.getTapeScoreByIdAndUser(s.getVedioID(), s.getUserID());
					if(this.getUserInfo().getRoles().contains(8)){
						examiners = vedioscoreService.findAllExaminer();
					}
					return INPUT;
				}
				vedioscoreService.saveVedioScore(s);
			}
		}
		this.addActionMessage("打分成功。");
		return SUCCESS;
	}
	
	public String completeExamine() throws Exception{
		vedioscoreService.updateTapeExamineStatus(tapeScore);
		return SUCCESS;
	}
	
	public void setUnExaminedTable(JSONDataTable unExaminedTable) {
		this.unExaminedTable = unExaminedTable;
	}

	public JSONDataTable getUnExaminedTable() {
		return unExaminedTable;
	}

	public void setExaminedTable(JSONDataTable examinedTable) {
		this.examinedTable = examinedTable;
	}

	public JSONDataTable getExaminedTable() {
		return examinedTable;
	}
	
	public IVedioscoreService getVedioscoreService() {
		return vedioscoreService;
	}

	public void setVedioscoreService(IVedioscoreService vedioscoreService) {
		this.vedioscoreService = vedioscoreService;
	}

	public void setTapeScore(VedioScoreVO tapeScore) {
		this.tapeScore = tapeScore;
	}

	public VedioScoreVO getTapeScore() {
		return tapeScore;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getVid() {
		return vid;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVname() {
		return vname;
	}

	public void setVediotapeService(IVediotapeService vediotapeService) {
		this.vediotapeService = vediotapeService;
	}

	public IVediotapeService getVediotapeService() {
		return vediotapeService;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setPerform(String perform) {
		this.perform = perform;
	}

	public String getPerform() {
		return perform;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setExaminers(List<User> examiners) {
		this.examiners = examiners;
	}

	public List<User> getExaminers() {
		return examiners;
	}
	
	public String getExaminerByInputer() {
		return examinerByInputer;
	}

	public void setExaminerByInputer(String examinerByInputer) {
		this.examinerByInputer = examinerByInputer;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public String getNewResult() {
		return newResult;
	}

	public void setNewResult(String newResult) {
		this.newResult = newResult;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}