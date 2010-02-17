package com.vms.action.arrange;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.beans.Pair;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IArrangeService;
import com.vms.service.iface.IVediotapeService;

public class ArrangeAction extends BaseAction {

	private static final long serialVersionUID = 4209670541352151353L;
	
	private static Logger logger = Logger.getLogger(ArrangeAction.class);
	
	private IVediotapeService tapeService;
	
	private IArrangeService arrangeService;
	
	private JSONDataTable unArrangedTable;
	
	private JSONDataTable arrangeTable;
	
	private List<Pair> monthList = new ArrayList<Pair>();
	
	private String month;
	
	private String newResult;
	
	public String toArrange() throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int nowMonth = calendar.getTime().getMonth();
		int nowYear = calendar.getTime().getYear();
		for(int i=0;i<6;i++){
			int month = nowMonth + i + 1;
			if(month > 12){
				month = month - 12;
				nowYear++;
			}
			String key = nowYear + "-" + month;
			monthList.add(new Pair(key, month + "月"));
		}
		return SUCCESS;
	}
	
	public String getUnarrangedTapes() throws Exception {
		unArrangedTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		int status = 3;
		try {
			List<Vediotape> tapes = tapeService.findVideotapeByStatus(
					status, 
					unArrangedTable.getSort(),
					unArrangedTable.getStartIndex(), 
					unArrangedTable.getStartIndex()+unArrangedTable.getRowsPerPage(), 
					unArrangedTable.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			
			List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
			for(Vediotape tape:tapes){
				tapeVOs.add(new VedioTapeVO(tape));
			}
			JSONDataTableUtils.setupJSONDataTable(tapeVOs, unArrangedTable, tapeService.getTotalCountForVideosByStatus(status));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String getArrangedTapes() throws Exception {
		//get amount days of selected month
		Date selDate = new Date();
		if(null != month && !"".equals(month)){
			String[] xx = month.split("-");
			selDate.setYear(Integer.parseInt(xx[0]));
			selDate.setMonth(Integer.parseInt(xx[1])-1);
		}
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(selDate);
		int numDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		arrangeTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<VedioTapeVO> tapes = arrangeService.findArrangedTapes(selDate);
			//make records the date of which has no tape to play.
			Date pDate = tapes.get(0).getPlayDate();
			for(int i=1;i<=numDayOfMonth;i++){
				boolean insert = true;
				for(VedioTapeVO tape:tapes){
					if(tape.getPlayDate().getDate() == i){
						insert = false;
						break;
					}
				}
				if(insert){
					VedioTapeVO voidTape = new VedioTapeVO();
					Date playDate = new Date(pDate.getYear(),pDate.getMonth(),i);
					voidTape.setPlayDate(playDate);
					tapes.add(i-1,voidTape);
				}
			}
			
			JSONDataTableUtils.setupJSONDataTable(tapes, arrangeTable, numDayOfMonth);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String doArrange() throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(newResult);
		if(jsonArray.isArray() && !jsonArray.isEmpty()){
			int size = jsonArray.size();
			for (int i = 0; i < size; i++) {
				
			}
		}
		return SUCCESS;
	}
	
	

	public void setTapeService(IVediotapeService tapeService) {
		this.tapeService = tapeService;
	}

	public IVediotapeService getTapeService() {
		return tapeService;
	}

	public void setUnArrangedTable(JSONDataTable unArrangedTable) {
		this.unArrangedTable = unArrangedTable;
	}

	public JSONDataTable getUnArrangedTable() {
		return unArrangedTable;
	}

	public void setNewResult(String newResult) {
		this.newResult = newResult;
	}

	public String getNewResult() {
		return newResult;
	}

	public void setArrangeTable(JSONDataTable arrangeTable) {
		this.arrangeTable = arrangeTable;
	}

	public JSONDataTable getArrangeTable() {
		return arrangeTable;
	}

	public void setArrangeService(IArrangeService arrangeService) {
		this.arrangeService = arrangeService;
	}

	public IArrangeService getArrangeService() {
		return arrangeService;
	}

	public void setMonthList(List<Pair> monthList) {
		this.monthList = monthList;
	}

	public List<Pair> getMonthList() {
		return monthList;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}
}
