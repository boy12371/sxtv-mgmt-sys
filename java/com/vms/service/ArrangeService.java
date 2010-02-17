package com.vms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Playorder;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.service.iface.IArrangeService;

public class ArrangeService implements IArrangeService{
	
	private IPlayorderDAO playorderDAO;
	
	public List<VedioTapeVO> findArrangedTapes(Date month) throws Exception{
		List<VedioTapeVO> tapes = new  ArrayList<VedioTapeVO>();
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(month);
		
		Date startDate = new Date(month.getYear(),month.getMonth(),1);
		Date endDate = new Date(month.getYear(),month.getMonth(),calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		List<Playorder> orders = playorderDAO.findMonthPlayOrder(startDate,endDate,-1,-1,true);
		
		for(Playorder p:orders){
			VedioTapeVO tape = new VedioTapeVO(p.getVedioID());
			tape.setPlayDate(p.getPlayDate());
			tapes.add(tape);
		}
		return tapes;
	}

	public void setPlayorderDAO(IPlayorderDAO playorderDAO) {
		this.playorderDAO = playorderDAO;
	}

	public IPlayorderDAO getPlayorderDAO() {
		return playorderDAO;
	}

}
