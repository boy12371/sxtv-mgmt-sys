package com.vms.action.accuracy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.vms.db.bean.Subject;
import com.vms.service.iface.IAccuracyService;

public class AccuracyAction extends BaseAction {

	private static final long serialVersionUID = 1590362405031808487L;

	private static Logger logger = Logger.getLogger(AccuracyAction.class);

	private IAccuracyService accuracyService;

	private JSONDataTable accuracyTable;
	
	private String startDateStr;
	
	private String endDateStr;
	
	private String examiner;
	
	private List<Subject> subjects;
	
	private int selSubject;
	
	public String toAccuracy() throws Exception{
		Date startDate = new Date();
		startDate.setDate(1);
		startDate.setMonth(startDate.getMonth()-1);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		
		int numDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date endDate = new Date();
		endDate.setMonth(endDate.getMonth()-1);
		endDate.setDate(numDayOfMonth);
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		startDateStr = dFormat.format(startDate);
		endDateStr = dFormat.format(endDate);
		
		setSubjects(accuracyService.getAllSubjects());
		return SUCCESS;
	}
	
	public String getAccuracy() throws Exception {
		accuracyTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(null==startDateStr || "".equals(startDateStr) || null==endDateStr || "".equals(endDateStr)){
			toAccuracy();
		}
		try {
			Date startDate = dFormat.parse(startDateStr);
			Date endDate = dFormat.parse(endDateStr);
			List<AccuracyVO> accs = accuracyService.findAllAccuracy(startDate, endDate, selSubject);
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

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSelSubject(int selSubject) {
		this.selSubject = selSubject;
	}

	public int getSelSubject() {
		return selSubject;
	}
}
