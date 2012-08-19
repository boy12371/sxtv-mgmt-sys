package com.vms.db.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.vms.db.bean.Scorelevel;
import com.vms.db.dao.iface.IScorelevelDAO;

public class ScorelevelDAO extends BaseRootDAO implements IScorelevelDAO {

	@Override
	public int getMaxScoreleveID() throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(Scorelevel.class);
		crt.setProjection(Projections.max(Scorelevel.PROP_LEVEL));
		return Integer.parseInt(crt.uniqueResult().toString());
		
	}

	

}
