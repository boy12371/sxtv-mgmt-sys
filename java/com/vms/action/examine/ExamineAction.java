package com.vms.action.examine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.CommonVariable;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IVedioscoreService;
import com.vms.service.iface.IVediotapeService;

public class ExamineAction extends BaseAction {
	
	private static final long serialVersionUID = -4049982603104035005L;
	
	private static Logger logger = Logger.getLogger(ExamineAction.class);
	
	private IVedioscoreService vedioscoreService;
	
	private IVediotapeService vediotapeService;

	private JSONDataTable unExaminedTable;
	
	private JSONDataTable examinedTable;
	
	private VedioScoreVO tapeScore;
	
	private String vid;
	
	private String vname;
	
	private String uid;
	
	private String perform;
	
	private String errorMsg;

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
				tapes = vedioscoreService.getAllUnExaminedVedioes(unExaminedTable.getStartIndex(), unExaminedTable.getStartIndex()+ unExaminedTable.getRowsPerPage());
				Status status = new Status(1);
				JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, vedioscoreService.getVedioCountByStatus(status));
			}else{
				List<Vediotape> list;
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
				tapes= new ArrayList<VedioTapeVO>();
				for (Vediotape tape : list) {
					tapes.add(new VedioTapeVO(tape));
				}
				JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, 1);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getStackTrace());
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
						examinedTable.getStartIndex()+ examinedTable.getRowsPerPage(),
						examinedTable.getSort(), examinedTable.getDir().equals("JSONDataTableUtils.SORT_DIRECTION")
						);
			}else{
				Vediotape tape = vediotapeService.getVediotapeByName(vname);
				if(null != tape){
					vid = tape.getId();
					scores = vedioscoreService.findUserExamineScoreByVideoId(
						tape.getId(), 
						examinedTable.getStartIndex(), 
						examinedTable.getStartIndex()+ examinedTable.getRowsPerPage(),
						examinedTable.getSort(), examinedTable.getDir().equals("JSONDataTableUtils.SORT_DIRECTION")
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
		if("modify".equals(perform)){
			tapeScore = vedioscoreService.getTapeScoreByIdAndUser(tapeScore.getVedioID(), Integer.parseInt(uid));
		}else{
			String name = vedioscoreService.getTapeByID(tapeScore.getVedioID()).getName();		
			tapeScore.setVedioName(name);
		}
		
		return SUCCESS;
	}
	
	public String doExamineTape() throws Exception{	
		int userID;
		if("modify".equals(perform)){
			userID = Integer.parseInt(uid);
		}else{
			userID = this.getUserInfo().getUserId();
		}
		tapeScore.setUserID(userID);
		vedioscoreService.saveVedioScore(tapeScore);
		toExamineTape();
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
}