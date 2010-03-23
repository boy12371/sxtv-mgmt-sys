package com.vms.service;

import java.util.List;

import com.vms.db.bean.Scoreweight;
import com.vms.db.dao.iface.IScoreweightDAO;
import com.vms.service.iface.IScoreweightService;

public class ScoreweightService implements IScoreweightService {
	private Class clz =Scoreweight.class;
	private IScoreweightDAO scoreweightDAO;

	@Override
	public void createWeight(Scoreweight weight) throws Exception {
		// TODO Auto-generated method stub
		this.scoreweightDAO.saveObject(weight);
		
	}

	@Override
	public void deleteWeight(int id) throws Exception {
		// TODO Auto-generated method stub
		Scoreweight weight = (Scoreweight) this.scoreweightDAO.getObject(clz, id);
		this.scoreweightDAO.deleteObject(weight);
	}

	@Override
	public List<Scoreweight> findAllScoreweight(int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<Scoreweight>)this.scoreweightDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
	}

	@Override
	public void updateWeight(Scoreweight weight) throws Exception {
		// TODO Auto-generated method stub
		Scoreweight wei = (Scoreweight) this.scoreweightDAO.getObject(clz, weight.getId());
		wei.setWieght(weight.getWieght());
		this.scoreweightDAO.saveOrUpdateObject(wei);

	}

	public IScoreweightDAO getScoreweightDAO() {
		return scoreweightDAO;
	}

	public void setScoreweightDAO(IScoreweightDAO scoreweightDAO) {
		this.scoreweightDAO = scoreweightDAO;
	}

	@Override
	public int getTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return this.scoreweightDAO.getObjectTotalCount(clz, Scoreweight.PROP_ID);
	}

}
