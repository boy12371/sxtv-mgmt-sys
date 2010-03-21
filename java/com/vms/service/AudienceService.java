package com.vms.service;

import java.util.List;

import com.vms.db.bean.Audience;
import com.vms.db.dao.iface.IAudienceDAO;
import com.vms.service.iface.IAudicenceService;

public class AudienceService implements IAudicenceService {

	private IAudienceDAO audienceDAO;
	private Class clz = Audience.class;

	@Override
	public void addAudience(Audience audience) throws Exception {
		// TODO Auto-generated method stub
		this.audienceDAO.saveObject(audience);
	}

	@Override
	public void deleteAudience(Audience audience) throws Exception {
		// TODO Auto-generated method stub
		this.audienceDAO.deleteObject(audience);
	}

	@Override
	public void deleteAudience(int id) throws Exception {
		// TODO Auto-generated method stub
		this.deleteAudience((Audience) audienceDAO.getObject(clz, id));
	}

	@Override
	public List<Audience> findAudience(int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<Audience>)this.audienceDAO.findObjectByFields(clz, null, startIndex,
				endIndex, propertyName, ascending);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return this.audienceDAO.getTotalCount_findObjectByFields(clz, null);
	}

	public IAudienceDAO getAudienceDAO() {
		return audienceDAO;
	}

	public void setAudienceDAO(IAudienceDAO audienceDAO) {
		this.audienceDAO = audienceDAO;
	}

}
