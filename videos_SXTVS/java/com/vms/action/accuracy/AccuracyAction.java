package com.vms.action.accuracy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.AccuracyVO;
import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.service.iface.IAccuracyService;
import com.vms.service.iface.IArrangeService;

public class AccuracyAction extends BaseAction {

	private static final long serialVersionUID = 1590362405031808487L;

	private static Logger logger = Logger.getLogger(AccuracyAction.class);

	private IAccuracyService accuracyService;
	
	private IArrangeService arrangeService;

	private JSONDataTable accuracyTable;
	
	private String startDate;
	private String endDate;
	private String examiner;
	
	public String toAccuracyPrint() throws Exception{
		return SUCCESS;
	}
	
	public String toAccuracy() throws Exception{
		return SUCCESS;
	}
	
	public String getAccuracy() throws Exception {
		accuracyTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start = dFormat.parse(startDate);
		Date end = dFormat.parse(endDate);
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(start);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		start = cal.getTime();
		
		cal.setTime(end); 
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		end = cal.getTime();
		try {
			List<AccuracyVO> accs = accuracyService.findAllAccuracy(start, end, -1);
			if (null != examiner && "".equals(examiner)) {
				AccuracyVO temp = null;
				for (AccuracyVO a : accs) {
					if (examiner.equals(a.getEmployeeName())) {
						temp = a;
						break;
					}
				}
				accs.clear();
				if (null != temp) {
					accs.add(temp);
				}
			}

			AccuracyComparator acomp = new AccuracyComparator();
			Collections.sort(accs, acomp);
			JSONDataTableUtils.setupJSONDataTable(accs, accuracyTable, accs.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public class AccuracyComparator implements Comparator<AccuracyVO> {
		public int compare(AccuracyVO o1, AccuracyVO o2) {
			return o1.getAccuracy()==o2.getAccuracy() ? 0 : (o1.getAccuracy()>o2.getAccuracy()?-1:1);
		 }
	}

	public void setAccuracyService(IAccuracyService accuracyService) {
		this.accuracyService = accuracyService;
	}

	public IAccuracyService getAccuracyService() {
		return accuracyService;
	}

	public IArrangeService getArrangeService() {
		return arrangeService;
	}

	public void setArrangeService(IArrangeService arrangeService) {
		this.arrangeService = arrangeService;
	}

	public void setAccuracyTable(JSONDataTable accuracyTable) {
		this.accuracyTable = accuracyTable;
	}

	public JSONDataTable getAccuracyTable() {
		return accuracyTable;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getExaminer() {
		return examiner;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
