package com.vms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vms.beans.AccuracyVO;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.db.dao.iface.IVedioscoreDAO;

public class AccuracyService {
	private IPlayorderDAO playorderDAO;
	private IVedioscoreDAO vedioscoreDAO;
	
	public List<AccuracyVO> findAllAccuracy(Date startDate, Date endDate) throws Exception{
		List<AccuracyVO> accs = new ArrayList<AccuracyVO>();
		List<Playorder> playorder = playorderDAO.findPlayorderBetweenDateWithFeedback(startDate, endDate);
		List<User> users = vedioscoreDAO.findAllExaminer();
		List<Vediotape> tapes = new ArrayList<Vediotape>();
		for(Playorder p:playorder){
			tapes.add(p.getVedioID());
		}
		
		for(User user:users){
			List<Vedioscore> uScore = vedioscoreDAO.findScoresOfUserAndTapes(user, tapes);
			AccuracyVO acc = new AccuracyVO();
			acc.setUserName(user.getUserName());
			acc.setEmployeeName(user.getEmployee().getName());
			acc.setAccuracy(computeAccuracyOfTapes(uScore));
			accs.add(acc);
		}
		return accs;
	}
	
	private float computeAccuracyOfTapes(List<Vedioscore> scores){
		float accSum = 0;
		float mSum = 0;
		for(Vedioscore s:scores){
			float uScore = s.getScore();
			//FIXME the way to compute mScore should be fix.
			float mScore = (s.getVedio().getAudienceRating() + s.getVedio().getMarketShare()) /2;
			accSum += Math.abs(uScore - mScore);
			mSum += mScore;
		}
		float acc = (1 - accSum / mSum) * 100 ;
		return acc;
	}
	
	public void setPlayorderDAO(IPlayorderDAO playorderDAO) {
		this.playorderDAO = playorderDAO;
	}

	public IPlayorderDAO getPlayorderDAO() {
		return playorderDAO;
	}
}
