package com.vms.action.examine;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Status;
import com.vms.service.VedioscoreService;
import com.vms.service.iface.IVedioscoreService;

public class ExamineAction extends BaseAction {
	
	private static final long serialVersionUID = -4049982603104035005L;
	
	private static Logger logger = Logger.getLogger(ExamineAction.class);
	
	private IVedioscoreService service = new VedioscoreService();
	
	public IVedioscoreService getService() {
		return service;
	}

	public void setService(IVedioscoreService service) {
		this.service = service;
	}

	private JSONDataTable unExaminedTable;
	
	public String toUnExaminedTapes() {
		return SUCCESS;
	}
	
	public String getUnExaminedTapes() throws Exception {
		unExaminedTable = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<VedioTapeVO> tapes = service.getAllUnExaminedVedioes(unExaminedTable.getStartIndex(), unExaminedTable.getStartIndex()+ unExaminedTable.getRowsPerPage());
			Status status = new Status(1);
			JSONDataTableUtils.setupJSONDataTable(tapes, unExaminedTable, service.getVedioTotalCountByStatus(status));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
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
	
}