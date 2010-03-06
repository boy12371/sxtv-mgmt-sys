package com.vms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vms.beans.AccuracyVO;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.User;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.db.dao.iface.IVedioscoreDAO;

public class AccuracyService {
	private IPlayorderDAO playorderDAO;
	private IVedioscoreDAO vedioscoreDAO;
	
	public List<AccuracyVO> findAllAccuracy(Date startDate, Date endDate) throws Exception{
		List<AccuracyVO> accs = new ArrayList<AccuracyVO>();
		List<Playorder> playorder = playorderDAO.findPlayorderBetweenDateWithFeedback(startDate, endDate);
		List<User> users = vedioscoreDAO.findAllExaminer();
		
		return accs;
	}

	
	public void setPlayorderDAO(IPlayorderDAO playorderDAO) {
		this.playorderDAO = playorderDAO;
	}

	public IPlayorderDAO getPlayorderDAO() {
		return playorderDAO;
	}
}
