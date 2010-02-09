package com.vms.db.dao;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sun.org.apache.xpath.internal.Expression;
import com.vms.common.DaoUtils;
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
	
	public List<Vediotape> findVedioesByStatus(Status status ,int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(Vediotape.PROP_STATUS, status);
		
		List<Vediotape> tapes = this.findObjectByFields(clz,conditions,startIndex,endIndex,propertyName,ascending);
		return tapes;
	}
	
	public List<Vediotape> findVedioesByColumn(Status status ,int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(Vediotape.PROP_STATUS, status);
		
		List<Vediotape> tapes = this.findObjectByFields(clz,conditions,startIndex,endIndex,propertyName,ascending);
		return tapes;
	}
	
	@Override
	public void saveObjects(List<Vediotape> objects) {
		// TODO Auto-generated method stub		
		for ( int i=0; i < objects.size() ; i++ ) {		    
			this.getHibernateTemplate().save(objects.get(i));
		}		  
		 		
	}
	
	public int getVedioTotalCountByStatus(Status status) throws Exception {
		return this.getObjectTotalCountByFields(clz, Vediotape.PROP_STATUS, status);
	}

	@Override
	public List<Vediotape> findVedioesInPeriod(Date dateStart, Date dateEnd, Map<String, Object> propertiesValues,
			int startIndex, int endIndex, String propertyName, boolean ascending) {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);	
	
		if (propertiesValues != null) {
			Set<String> keys = propertiesValues.keySet();
			Iterator<String> it = keys.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();			
				crt.add(Restrictions.between(Vediotape.PROP_DATE_INPUT,dateStart,dateEnd));
			}
		}

		Order order = DaoUtils.getOrder(propertyName, ascending);
		if (order != null) {
			crt.addOrder(order);
		}
		if (startIndex != -1 || endIndex != -1) {
			crt.setFirstResult(startIndex);
			crt.setMaxResults(endIndex);
		}
		return crt.list();
		
	}

	

}