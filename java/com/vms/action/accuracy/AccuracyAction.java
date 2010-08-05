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
	
	private String selDate;
	
	private List<String> years = new ArrayList<String>();
	
	private String defYear;
	
	private String defMonth;
	
	private String examiner;
	
	public String toAccuracyPrint() throws Exception{
		return SUCCESS;
	}
	
	public String toAccuracy() throws Exception{
		Date fDate = arrangeService.getFirstArrangedDate();
		if(null==fDate) {
			this.addActionError("暂无数据。");
			return SUCCESS;
		}
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		String firDate = dateFm.format(fDate);
		Integer year1st = Integer.parseInt(firDate.split("-")[0]);
		String nowDate = dateFm.format(new Date());
		Integer curYear = Integer.parseInt(nowDate.split("-")[0]);
		Integer curMonth = Integer.parseInt(nowDate.split("-")[1]);
		for(int i=year1st;i<=curYear;i++){
			years.add(i+"");
		}
		defYear = curYear.toString();
		defMonth = "01";
		return SUCCESS;
	}
	
	public String getAccuracy() throws Exception {
		accuracyTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tempDate = selDate + "-01";
		Date startDate = dFormat.parse(tempDate);
		//get the end of month
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		int numDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		tempDate = selDate + "-" + numDayOfMonth;
		Date endDate = dFormat.parse(tempDate);
		try {
			List<AccuracyVO> accs = accuracyService.findAllAccuracy(startDate, endDate, -1);
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

	public void setSelDate(String selDate) {
		this.selDate = selDate;
	}

	public String getSelDate() {
		return selDate;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<String> getYears() {
		return years;
	}

	public void setDefYear(String defYear) {
		this.defYear = defYear;
	}

	public String getDefYear() {
		return defYear;
	}

	public void setDefMonth(String defMonth) {
		this.defMonth = defMonth;
	}

	public String getDefMonth() {
		return defMonth;
	}
}
