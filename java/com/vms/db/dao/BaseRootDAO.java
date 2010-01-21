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
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vms.common.DaoUtils;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IBaseRootDAO;

public class BaseRootDAO implements IBaseRootDAO {
	private HibernateTemplate hibernateTemplate;

	private Criteria criteria;
	private Query query;

	@Override
	public void delete(Object object) throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(object);
	}

	@Override
	public Object get(Class clz, Serializable id) throws Exception {
		// TODO Auto-generated method stubCriteria
		return hibernateTemplate.get(clz, id);
	}

	@Override
	public Object load(Class clz, Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(clz, id);
	}

	@Override
	public Serializable save(Object object) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTemplate.save(object);

	}

	@Override
	public void saveOrUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(object);

	}

	@Override
	public void update(Object object) throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.update(object);
	}

	@Override
	public List findObjectByFields(Class clz,
			Map<String, Object> propertiesValues, int startIndex, int endIndex,
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
		if(endIndex!=-1){
			crt.setFirstResult(startIndex);
			crt.setMaxResults(endIndex);
		}
		
		return crt.list();
	}

	@Override
	public List findObjectByField(Class clz, String propertyName,
			Object value, int startIndex, int endIndex, boolean asceding)
			throws Exception {
		Criteria crt = this.getCriteria(clz);

		crt.add(Restrictions.eq(propertyName, value));
		crt.addOrder(DaoUtils.getOrder(propertyName, asceding));
		crt.setFirstResult(startIndex);
		crt.setMaxResults(endIndex);
		return crt.list();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public Criteria getCriteria(Class clz) {
		if (criteria == null) {

			Session session = this.hibernateTemplate.getSessionFactory()
					.getCurrentSession();
			criteria = session.createCriteria(clz);
		}
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Query getQuery(String hqlString) {
		if (query == null) {
			Session session = this.hibernateTemplate.getSessionFactory()
					.getCurrentSession();
			query = session.createQuery(hqlString);
		}
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

}