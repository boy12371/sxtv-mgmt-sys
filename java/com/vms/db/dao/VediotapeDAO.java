package com.vms.db.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IVediotapeDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class VediotapeDAO extends com.vms.db.dao.BaseRootDAO implements IVediotapeDAO {

	private Class clz = com.vms.db.bean.Vediotape.class;

	
	public List<Vediotape> findVedioesByStatus(Status status ,int startIndex, int endIndex) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(Vediotape.PROP_STATUS, status);
		
		List<Vediotape> tapes = this.findObjectByFields(clz,conditions,startIndex,endIndex,Vediotape.PROP_DATE_COMING,true);
		return tapes;
	}
	
	public int getVedioTotalCountByStatus(Status status) throws Exception {
		return this.getObjectTotalCountByFields(clz, Vediotape.PROP_STATUS, status);
	}

}