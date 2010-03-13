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
import com.vms.service.iface.IAccuracyService;

public class AccuracyService implements IAccuracyService{
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
			float vScore = 0;
			float rate = s.getVedio().getAudienceRating();
			if (rate < 0.7) {
				vScore = 0.6f;
			} else if (rate >= 0.7 && rate < 0.8) {
				vScore = 0.7f;
			} else if (rate >= 0.8 && rate < 0.9) {
				vScore = 0.8f;
			} else {
				vScore = 0.9f;
			}
			vScore *= 100;
			accSum += s.getAccuracy();
			mSum += vScore;
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
	
	public IVedioscoreDAO getVedioscoreDAO() {
		return vedioscoreDAO;
	}

	public void setVedioscoreDAO(IVedioscoreDAO vedioscoreDAO) {
		this.vedioscoreDAO = vedioscoreDAO;
	}

}
