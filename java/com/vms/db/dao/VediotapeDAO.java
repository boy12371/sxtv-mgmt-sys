package com.vms.db.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import org.hibernate.Session;

import com.vms.common.DaoUtils;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IVediotapeDAO;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class VediotapeDAO extends com.vms.db.dao.BaseRootDAO implements
		IVediotapeDAO {

	private Class clz = com.vms.db.bean.Vediotape.class;

	@SuppressWarnings("unchecked")
	@Override
	public List<Vediotape> findVediosByProperty(String propertyName,
			Object value, int startIndex, int endIndex, boolean ascending)
			throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);

		crt.add(Restrictions.eq(propertyName, value));
		crt.addOrder(DaoUtils.getOrder(propertyName, ascending));
		crt.setFirstResult(startIndex);
		crt.setMaxResults(endIndex);
		return (List<Vediotape>) crt.list();
	}

	
	@Override
	public List<Vediotape> findVediosByProperties(Map<String,Object> propertiesValues,
			int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		Set<String> keys = propertiesValues.keySet();
		Iterator<String> it =keys.iterator();
		
		while (it.hasNext()) {
			String key = (String) it.next();
			crt.add(Restrictions.eq(key, propertiesValues.get(key)));			
		}
		crt.addOrder(DaoUtils.getOrder(propertyName, ascending));
		crt.setFirstResult(startIndex);
		crt.setMaxResults(endIndex);
		List list = crt.list();
		return list;
	}

	@Override
	public Vediotape getUniqueVedioByProperty(String propertyName, Object value)
			throws Exception {
		// TODO Auto-generated method stub

		Criteria crt = this.getCriteria(clz);

		crt.add(Restrictions.naturalId().set(Vediotape.PROP_VEDIO_NAME, value));

		return (Vediotape) crt.uniqueResult();
	}

	@Override
	public List<Vediotape> findVediosByProperties(Map<String,Object> propertiesValues)
			throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		Set<String> keys = propertiesValues.keySet();
		Iterator<String> it =keys.iterator();
		while (keys.iterator().hasNext()) {
			String key = (String) it.next();
			crt.add(Restrictions.eq(key, propertiesValues.get(key)));			
		}	
		return (List<Vediotape>)crt.list();		
	}

	@Override
	public List<Vediotape> findVediosByProperty(String propertyName,
			Object value) throws Exception {
		Criteria crt = this.getCriteria(clz);
		crt.add(Restrictions.eq(propertyName, value));
		return (List<Vediotape>) crt.list();
		// TODO Auto-generated method stub

	}


	@Override
	public List<Vediotape> findVediosInPeriod(Date start, Date end,
			Map<String, Object> propertiesValues) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveVedios(List<Vediotape> vedios) throws Exception {
		// TODO Auto-generated method stub
		HibernateTemplate templateDao =this.getHibernateTemplate();
		for (Vediotape vediotape : vedios) {
			templateDao.save(vediotape);
		}
	}

}