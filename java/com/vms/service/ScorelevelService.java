package com.vms.service;

import java.util.List;

import com.vms.db.bean.Scorelevel;
import com.vms.db.dao.iface.IScorelevelDAO;
import com.vms.service.iface.IScorelevelService;

public class ScorelevelService implements IScorelevelService {

	private Class clz = Scorelevel.class;
	private IScorelevelDAO scorelevelDAO;

	@Override
	public void createLevel(Scorelevel level) throws Exception {
		// TODO Auto-generated method stub
		this.scorelevelDAO.saveObject(level);

	}

	@Override
	public void deleteLevel(int id) throws Exception {
		// TODO Auto-generated method stub
		Scorelevel level = (Scorelevel) this.scorelevelDAO.getObject(clz, id);
		this.scorelevelDAO.deleteObject(level);
	}

	@Override
	public List<Scorelevel> findAllScorelevel(int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<Scorelevel>) this.scorelevelDAO.findObjectByFields(clz,
				null, startIndex, endIndex, propertyName, ascending);
	}

	@Override
	public void updateLevel(Scorelevel level) throws Exception {
		// TODO Auto-generated method stub
		Scorelevel lev = (Scorelevel) this.scorelevelDAO.getObject(clz, level
				.getId());
		lev.setComments(level.getComments());
		lev.setEnd(level.getEnd());
		lev.setLevel(level.getLevel());
		lev.setStart(level.getStart());
		this.scorelevelDAO.saveOrUpdateObject(lev);
	}

	public IScorelevelDAO getScorelevelDAO() {
		return scorelevelDAO;
	}

	public void setScorelevelDAO(IScorelevelDAO scorelevelDAO) {
		this.scorelevelDAO = scorelevelDAO;
	}

	@Override
	public int getTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return this.scorelevelDAO.getObjectTotalCount(clz, Scorelevel.PROP_ID);
	}

}
