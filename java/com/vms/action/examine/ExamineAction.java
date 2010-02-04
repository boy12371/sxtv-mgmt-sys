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
	
	private IVedioscoreService service;

	private JSONDataTable unExaminedTable;
	
	private JSONDataTable examinedTable;
	
	public String toUnExaminedTapes() {
		return SUCCESS;
	}
	
	public String toExaminedTapes() {
		return SUCCESS;
	}
	
	public String getUnExaminedTapes() throws Exception {
		unExaminedTable = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<VedioTapeVO> tapes = service.getAllUnExaminedVedioes(unExaminedTable.getStartIndex(), unExaminedTable.getStartIndex()+ unExaminedTable.getRowsPerPage());
			Status status = new Status(1);
			JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, service.getVedioCountByStatus(status));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String getExaminedTapes() throws Exception {
		examinedTable = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<VedioScoreVO> scores = service.getUserExaminedVedioes(
					getUserInfo().getUsername(),
					examinedTable.getStartIndex(), 
					examinedTable.getStartIndex()+ examinedTable.getRowsPerPage(),
					examinedTable.getSort(), examinedTable.getDir().equals("asc")					
			);
			JSONDataTableUtils.setupJSONDataTable(scores, examinedTable, service.getCountOfUserExaminedVedio(getUserInfo().getUsername()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
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

	public void setService(IVedioscoreService service) {
		this.service = service;
	}

	public IVedioscoreService getService() {
		return service;
	}
	
}