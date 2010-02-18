package com.vms.db.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vms.common.DaoUtils;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IBaseRootDAO;

public class BaseRootDAO extends HibernateDaoSupport implements IBaseRootDAO {

	@Override
	public void deleteObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public Object getObject(Class clz, Serializable id) throws Exception {
		// TODO Auto-generated method stubCriteria
		return this.getHibernateTemplate().get(clz, id);
	}

	@Override
	public Object loadObject(Class clz, Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().load(clz, id);
	}

	@Override
	public Serializable saveObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().save(object);

	}

	@Override
	public void saveOrUpdateObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(object);

	}

	@Override
	public void updateObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(object);
	}

	@Override
	public List findObjectByFields(Class clz, Map<String, Object> propertiesValues, int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);

		if (propertiesValues != null) {
			Set<String> keys = propertiesValues.keySet();
			Iterator<String> it = keys.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				crt.add(Restrictions.eq(key, propertiesValues.get(key)));
			}
		}

		Order order = DaoUtils.getOrder(propertyName, ascending);
		if (order != null) {
			crt.addOrder(order);
		}
		if (startIndex != -1 && endIndex != -1) {
			crt.setFirstResult(startIndex);
			crt.setMaxResults(endIndex);
		}

		return crt.list();
	}

	@Override
	public List findObjectByField(Class clz, String propertyName, Object value, int startIndex, int endIndex, String orderPropertyName, boolean asceding) throws Exception {
		Criteria crt = this.getCriteria(clz);

		crt.add(Restrictions.eq(propertyName, value));
		Order order = DaoUtils.getOrder(orderPropertyName, asceding);
		if (order != null) {
			crt.addOrder(order);
		}
		if (startIndex != -1 && endIndex != -1) {
			crt.setFirstResult(startIndex);
			crt.setMaxResults(endIndex);
		}
		return crt.list();
	}

	@Override
	public int getObjectTotalCount(Class clz, String propertyName) throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		crt.setProjection(Projections.count(propertyName));
		return Integer.parseInt(crt.uniqueResult().toString());
	}

//	@Override
//	public int getObjectTotalCountByFields(Class clz, String propertyName, Object value) throws Exception {
//		if (null == value)
//			return getObjectTotalCount(clz, propertyName);
//		Criteria crt = this.getCriteria(clz);
//		crt.add(Restrictions.eq(propertyName, value));
//		crt.setProjection(Projections.rowCount());
//		return Integer.parseInt(crt.uniqueResult().toString());
//	}

	@Override
	public List findAll(Class clz) throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		return crt.list();
	}
	
	@Override
	public Object getUniqueResultByProperty(Class clz, Map<String, Object> propertiesValues) throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		if (propertiesValues != null) {
			Set<String> keys = propertiesValues.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				crt.add(Restrictions.eq(key, propertiesValues.get(key)));
			}
		}
		return crt.uniqueResult();

	}

	public Query getQuery(String hqlString) {		
		return this.getSession().createQuery(hqlString);
	}

	
	public Criteria getCriteria(Class clz) {
		return this.getSession().createCriteria(clz);
	}

	@Override
	public int getTotalCount_findObjectByField(Class clz, String propertyName, Object value) {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		crt.add(Restrictions.eq(propertyName, value));
		crt.setProjection(Projections.rowCount());
		return Integer.parseInt(crt.uniqueResult().toString());
		
	}

	@Override
	public int getTotalCount_findObjectByFields(Class clz, Map<String, Object> propertiesValues) {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		if (propertiesValues != null) {
			Set<String> keys = propertiesValues.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				crt.add(Restrictions.eq(key, propertiesValues.get(key)));
			}
		}
		crt.setProjection(Projections.rowCount());
		return Integer.parseInt(crt.uniqueResult().toString());
		
	}

	

}