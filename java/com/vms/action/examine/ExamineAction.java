package com.vms.action.examine;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Status;
import com.vms.service.iface.IVedioscoreService;

public class ExamineAction extends BaseAction {
	
	private static final long serialVersionUID = -4049982603104035005L;
	
	private static Logger logger = Logger.getLogger(ExamineAction.class);
	
	private IVedioscoreService vedioscoreService;

	private JSONDataTable unExaminedTable;
	
	private JSONDataTable examinedTable;
	
	private VedioScoreVO tapeScore;
	
	public String toUnExaminedTapes() {
		return SUCCESS;
	}
	
	public String toExaminedTapes() {
		return SUCCESS;
	}
	
	public String getUnExaminedTapes() throws Exception {
		unExaminedTable = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<VedioTapeVO> tapes = vedioscoreService.getAllUnExaminedVedioes(unExaminedTable.getStartIndex(), unExaminedTable.getStartIndex()+ unExaminedTable.getRowsPerPage());
			Status status = new Status(1);
			JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, vedioscoreService.getVedioCountByStatus(status));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String getExaminedTapes() throws Exception {
		examinedTable = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<VedioScoreVO> scores = vedioscoreService.getUserExaminedVedioes(
					getUserInfo().getUsername(),
					examinedTable.getStartIndex(), 
					examinedTable.getStartIndex()+ examinedTable.getRowsPerPage(),
					examinedTable.getSort(), examinedTable.getDir().equals("JSONDataTableUtils.SORT_DIRECTION")					
			);
			JSONDataTableUtils.setupJSONDataTable(scores, examinedTable, vedioscoreService.getCountOfUserExaminedVedio(getUserInfo().getUsername()));
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
		String name = vedioscoreService.getTapeByID(tapeScore.getVedioID()).getName();		
		tapeScore.setVedioName(name);
		return SUCCESS;
	}
	
	public String doExamineTape() throws Exception{	
		tapeScore.setExaminer(this.getUserInfo().getUsername());
		vedioscoreService.saveVedioScore(tapeScore);
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
}