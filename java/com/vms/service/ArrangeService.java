package com.vms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.service.iface.IArrangeService;

public class ArrangeService implements IArrangeService{
	
	private IPlayorderDAO playorderDAO;
	
	public Date getFirstArrangedDate() throws Exception {
		return playorderDAO.getFirstArrangedDate();
	}
	
	public List<Playorder> findPlayorders(Date month) throws Exception{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(month);
		
		Date startDate = new Date(month.getYear(),month.getMonth(),1);
		Date endDate = new Date(month.getYear(),month.getMonth(),calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		List<Playorder> orders = playorderDAO.findMonthPlayOrder(startDate,endDate,-1,-1,true);
		return orders;
	}
	
	public List<VedioTapeVO> findArrangedTapes(Date month) throws Exception{
		List<VedioTapeVO> tapes = new  ArrayList<VedioTapeVO>();
		
		List<Playorder> orders = findPlayorders(month);
		
		for(Playorder p:orders){
			VedioTapeVO tape = new VedioTapeVO(p.getVedioID());
			tape.setPlayDate(p.getPlayDate());
			tapes.add(tape);
		}
		return tapes;
	}
	
	public void deletePlayOrder(List<Playorder> pos, int userID) throws Exception{
		playorderDAO.deletePlayorder(pos, userID);
	}
	
	public void savePlayorder(List<Playorder> pos,  int userID) throws Exception{
		playorderDAO.savePlayorder(pos, userID);
	}
	
	public void setPlayorderDAO(IPlayorderDAO playorderDAO) {
		this.playorderDAO = playorderDAO;
	}

	public IPlayorderDAO getPlayorderDAO() {
		return playorderDAO;
	}
	
	public Playchangelog getDelInfoFormArrangeLog(String videoID) throws Exception{
		List<Playchangelog> list = playorderDAO.findObjectByField(Playchangelog.class, Playchangelog.PROP_VEDIO_I_D, new Vediotape(videoID), -1, -1, Playchangelog.PROP_DATE, false);
		if(null==list || 0==list.size()) return null;
		if(list.get(0).getOperation().equals("移除")) return list.get(0);
		return null;
	}

}
