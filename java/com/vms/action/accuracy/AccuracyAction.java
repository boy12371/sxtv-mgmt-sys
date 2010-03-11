package com.vms.action.accuracy;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.AccuracyVO;
import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.service.iface.IAccuracyService;

public class AccuracyAction extends BaseAction {

	private static final long serialVersionUID = 1590362405031808487L;

	private static Logger logger = Logger.getLogger(AccuracyAction.class);

	private IAccuracyService accuracyService;

	private JSONDataTable accuracyTable;
	
	private String startDateStr;
	
	private String endDateStr;
	
	private String examiner;
	
	public String toAccuracy(){
		return SUCCESS;
	}
	
	public String getAccuracy() throws Exception {
		accuracyTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		Date startDate = new Date();
		Date endDate = new Date();
		List<AccuracyVO> accs = accuracyService.findAllAccuracy(startDate, endDate);
		if(null != examiner && "".equals(examiner)){
			AccuracyVO temp=null;
			for(AccuracyVO a:accs){
				if(examiner.equals(a.getEmployeeName())){
					temp = a;
					break;
				}
			}
			accs.clear();
			if(null != temp){
				accs.add(temp);
			}
		}
		JSONDataTableUtils.setupJSONDataTable(accs, accuracyTable, accs.size());
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

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getExaminer() {
		return examiner;
	}
}
