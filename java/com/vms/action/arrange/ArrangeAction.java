package com.vms.action.arrange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.beans.Pair;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.User;
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
	
	private String firstArrangedDate;
	
	private String nowDate;
	
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
		int status = 5;
		try {
			List<Vediotape> tapes = tapeService.findVideotapeByStatus(
					status, 
					unArrangedTable.getSort(),
					-1, 
					-1, 
					unArrangedTable.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			
			List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
			int pos = 0;
			for(Vediotape tape:tapes){
				//is this tape deleted from arranged list
				Playchangelog plog = arrangeService.getDelInfoFormArrangeLog(tape.getId());
				VedioTapeVO tapev = new VedioTapeVO(tape);
				if(null != plog){
					Date date = plog.getFromDate();
					DateFormat dFormat = new SimpleDateFormat("yyyy年MM月dd日");
					String time = dFormat.format(date);
					String comments = "该影带从已编排列表的" + time + "移除。";
					tapev.setMarked(9);
					tapev.setComments(comments);
					tapeVOs.add(pos, tapev);
					pos++;
				}else{
					tapev.setMarked(0);
					tapeVOs.add(tapev);
				}
			}
			JSONDataTableUtils.setupJSONDataTable(tapeVOs, unArrangedTable, tapeVOs.size());
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
		
		getArrangedTapesByDate(selDate);
		return SUCCESS;
	}
	
	public String doArrange() throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(newResult);
		List<Playorder> allOrders = new ArrayList<Playorder>();
		List<Playorder> newOrders = new ArrayList<Playorder>();
		if(jsonArray.isArray() && !jsonArray.isEmpty()){
			int size = jsonArray.size();
			for (int i = 0; i < size; i++) {
				JSONObject obj =jsonArray.getJSONObject(i);
				Playorder po = new Playorder();
				po.setVedioID(new Vediotape(obj.getString("id")));
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");         
				po.setPlayDate(dFormat.parse(obj.getString("playDate")));
				po.setArrangeDate(new Date());
				po.setAuditor(new User(getUserInfo().getUserId()));
				
				int marked = obj.getInt("marked");
				if(1<=marked){
					newOrders.add(po);
				}
				allOrders.add(po);
			}
		}
		arrangeService.savePlayorder(newOrders, this.getUserInfo().getUserId());
		//get old play order of select month to filter for records need to be deleted.
		Date selDate = new Date();
		String[] xx = month.split("-");
		selDate.setYear(Integer.parseInt(xx[0]));
		selDate.setMonth(Integer.parseInt(xx[1])-1);
		List<Playorder> oldOrders = arrangeService.findPlayorders(selDate);
		List<Playorder> delOrders = new ArrayList<Playorder>();
		for(Playorder oldOrder: oldOrders){
			boolean delFlag = true;
			for(Playorder order:allOrders){
				if(oldOrder.getVedioID().getId().equals(order.getVedioID().getId())){
					delFlag = false;
					break;
				}
			}
			if(delFlag) delOrders.add(oldOrder);
		}
		arrangeService.deletePlayOrder(delOrders, this.getUserInfo().getUserId());
		return SUCCESS;
	}
	
	public String toArrangedHistory() throws Exception{
		Date fDate = arrangeService.getFirstArrangedDate();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		firstArrangedDate = dateFm.format(fDate);
		nowDate = dateFm.format(new Date());
		return SUCCESS;
	}
	
	public String getArrangedHistory() throws Exception {
		Date selDate;
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		selDate = dFormat.parse(month + "-01");  
		
		getArrangedTapesByDate(selDate);
		return SUCCESS;
	}
	
	private void getArrangedTapesByDate(Date selDate) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(selDate);
		int numDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		arrangeTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<VedioTapeVO> tapes = arrangeService.findArrangedTapes(selDate);
			Date now = new Date();
			//make records the date of which has no tape to play.
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
					Date playDate = new Date(selDate.getYear(),selDate.getMonth(),i);
					voidTape.setPlayDate(playDate);
					tapes.add(i-1,voidTape);
				}
				if(now.getMonth()==selDate.getMonth() && now.getDate()>i ){
					tapes.get(i-1).setMarked(-1);
				}
			}
			
			JSONDataTableUtils.setupJSONDataTable(tapes, arrangeTable, numDayOfMonth);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
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

	public void setFirstArrangedDate(String firstArrangedDate) {
		this.firstArrangedDate = firstArrangedDate;
	}

	public String getFirstArrangedDate() {
		return firstArrangedDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public String getNowDate() {
		return nowDate;
	}

}
