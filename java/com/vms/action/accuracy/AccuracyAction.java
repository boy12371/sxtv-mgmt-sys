package com.vms.action.accuracy;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.service.iface.IAccuracyService;

public class AccuracyAction extends BaseAction {

	private static final long serialVersionUID = 1590362405031808487L;

	private static Logger logger = Logger.getLogger(AccuracyAction.class);

	private IAccuracyService accuracyService;

	private JSONDataTable accuracyTable;
	
	public String toAccuracy(){
		return SUCCESS;
	}
	
	public String getAccuracy() throws Exception {
		accuracyTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		return SUCCESS;
	}

	public void setAccuracyService(IAccuracyService accuracyService) {
		this.accuracyService = accuracyService;
	}

	public IAccuracyService getAccuracyService() {
		return accuracyService;
	}

	public void setAccuracyTable(JSONDataTable accuracyTable) {
		this.accuracyTable = accuracyTable;
	}

	public JSONDataTable getAccuracyTable() {
		return accuracyTable;
	}
}
