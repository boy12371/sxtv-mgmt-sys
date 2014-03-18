package com.vms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vms.beans.AccuracyVO;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Subject;
import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.db.dao.iface.IVedioscoreDAO;
import com.vms.service.iface.IAccuracyService;

public class AccuracyService implements IAccuracyService{
	private IPlayorderDAO playorderDAO;
	private IVedioscoreDAO vedioscoreDAO;

	public List<AccuracyVO> findAllAccuracy(Date startDate, Date endDate, int subject) throws Exception{
		List<AccuracyVO> accs = new ArrayList<AccuracyVO>();
		List<Playorder> playorder = playorderDAO.findPlayorderBetweenDateWithFeedback(startDate, endDate, subject);
		List<User> users = vedioscoreDAO.findAllExaminer();
		List<Vediotape> tapes = new ArrayList<Vediotape>();
		StringBuffer idParam = new StringBuffer();
		if(playorder.isEmpty()){
			return accs;
		}
		for(Playorder p:playorder){
			idParam.append("'"+p.getVedioID().getId()+"',");
			tapes.add(p.getVedioID());
		}
		for(User user:users){
			List<Vedioscore> uScore = vedioscoreDAO.findScoresOfUserAndTapes(user, idParam.substring(0, idParam.lastIndexOf(",")));
			if(null == uScore || 0 == uScore.size()){
				continue;
			}
			for(Vedioscore vs:uScore){
				for(Vediotape vt: tapes){
					if(vs.getVedio().getId().equals(vt.getId())){
						vs.getVedio().setScore(vt.getScore());
						break;
					}
				}
			}
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
			float uScore = s.getScore();//评分员打分的综合评分
			float mScore = s.getVedio().getScore();//依据收视率排名属于那个层次对应的得分（收视率得分）
			accSum += Math.abs(uScore - mScore);
			mSum += mScore;
		}
		float acc = (1 - accSum / mSum) * 100 ;
		return acc;
	}
	
	public List<Subject> getAllSubjects() throws Exception{
		return playorderDAO.findObjectByField(Subject.class, "status", 1, -1, -1, null, true);
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
