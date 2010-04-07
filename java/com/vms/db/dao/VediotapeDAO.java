package com.vms.db.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

import org.springframework.orm.hibernate3.HibernateCallback;
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

	public List<Vediotape> findVedioesByStatus(Status status, int startIndex, int endIndex) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(Vediotape.PROP_STATUS + ".id", status.getId());

		List<Vediotape> tapes = this.findObjectByFields(clz, conditions, startIndex, endIndex,
				Vediotape.PROP_DATE_COMING, true);
		return tapes;
	}

	public List<Vediotape> findVedioesByStatus(Status status, int startIndex, int endIndex, String propertyName,
			boolean ascending) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(Vediotape.PROP_STATUS, status);

		List<Vediotape> tapes = this.findObjectByFields(clz, conditions, startIndex, endIndex, propertyName, ascending);
		return tapes;
	}

	public List<Vediotape> findVedioesByColumn(Status status, int startIndex, int endIndex, String propertyName,
			boolean ascending) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(Vediotape.PROP_STATUS, status);

		List<Vediotape> tapes = this.findObjectByFields(clz, conditions, startIndex, endIndex, propertyName, ascending);
		return tapes;
	}

	@Override
	public void saveObjects(List<Vediotape> objects) {
		// TODO Auto-generated method stub
		for (int i = 0; i < objects.size(); i++) {
			this.getHibernateTemplate().save(objects.get(i));
		}

	}

	@Override
	public List<Vediotape> findVedioesInPeriod(Date dateStart, Date dateEnd, Map<String, Object> propertiesValues,
			int startIndex, int endIndex, String propertyName, boolean ascending) {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
//		crt.setCacheable(true);
		if (propertiesValues != null) {
			Set<String> keys = propertiesValues.keySet();
			Iterator<String> it = keys.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				crt.add(Restrictions.between(key, dateStart, dateEnd));
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
		return (List<Vediotape>) crt.list();

	}

	@Override
	public List<Vediotape> findAllVideosInScope(String scopeName, Object[] values, String propertyName, int startIndex,
			int endIndex, boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
//		crt.setCacheable(true);
		crt.add(Restrictions.in(scopeName, values));
		Order order = DaoUtils.getOrder(propertyName, asceding);
		if (order != null) {
			crt.addOrder(order);
		}
		if (startIndex != -1 && endIndex != -1) {
			crt.setFirstResult(startIndex);
			crt.setMaxResults(endIndex);
		}
		List<Vediotape> list = (List<Vediotape>) crt.list();
		return list;

	}

	@Override
	public int getTotalCountForAllVideotapesForAudit() throws Exception {
		// TODO Auto-generated method stub
		Criteria crt = this.getCriteria(clz);
		crt.add(Restrictions.in(Vediotape.PROP_STATUS, new Object[] { new Status(2), new Status(3), new Status(7), new Status(5) }));
		crt.setProjection(Projections.rowCount());
		return Integer.parseInt(crt.uniqueResult().toString());

	}

	@Override
	public int getVedioTotalCountByStatus(Status status) throws Exception {
		return this.getTotalCount_findObjectByField(clz, Vediotape.PROP_STATUS, status);
	}

	@Override
	public boolean updateVideotape(String hql, Object[] args) throws Exception {
		// TODO Auto-generated method stub
		int result = this.getHibernateTemplate().bulkUpdate(hql, args);
		return result != 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findVideos(final String hql, final Map<String, Object[]> valuesTypes, final int startIndex,
			final int endIndex) throws Exception {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(hql);
				if (valuesTypes != null && !valuesTypes.isEmpty()) {
					Object[] values = valuesTypes.get("values");
					Type[] types = (Type[]) valuesTypes.get("types");
					query.setParameters(values, types);
				}
				if (startIndex != -1 && endIndex != -1) {
					query.setFirstResult(startIndex);
					query.setMaxResults(endIndex);
				}
				return query.list();
			}

		});

	}

	@Override
	public List<Vediotape> findVideosByFieldsNamePartiallyDateInScope(Map<String, Object> fieldsValues, int startIndex,
			int endIndex, String orderProperty, boolean ascending) throws Exception {

		Criteria crt = this.getCriteria(clz);
//		crt.setCacheable(true);
		if (fieldsValues != null && !fieldsValues.isEmpty()) {
			Set<String> keys = fieldsValues.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if (key.equals(Vediotape.PROP_VEDIO_NAME)) {
					crt.add(Restrictions.ilike(key, "%"+fieldsValues.get(key)+"%"));
					
				}else if (key.equals("startDate")) {
					if (fieldsValues.get(key) != null) {
						crt.add(Restrictions.ge(Vediotape.PROP_DATE_COMING, fieldsValues.get(key)));
						
					}

				}else if (key.equals("endDate")) {
					if (fieldsValues.get(key) != null) {
						crt.add(Restrictions.le(Vediotape.PROP_DATE_COMING, fieldsValues.get(key)));
						
					}
				}else{
					crt.add(Restrictions.eq(key, fieldsValues.get(key)));	
				}
				
			}
		}
		Order order = DaoUtils.getOrder(orderProperty, ascending);
		if (order != null) {
			crt.addOrder(order);
		}
		if (startIndex != -1 && endIndex != -1) {
			crt.setFirstResult(startIndex);
			crt.setMaxResults(endIndex);
		}
		
		return (List<Vediotape>) crt.list();
	}
	
	
	@Override
	public int getTotalCountForVideosByFieldsNamePartiallyDateInScope(Map<String, Object> fieldsValues) throws Exception {

		Criteria crt = this.getCriteria(clz);

		if (fieldsValues != null && !fieldsValues.isEmpty()) {
			Set<String> keys = fieldsValues.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if (key.equals(Vediotape.PROP_VEDIO_NAME)) {
					crt.add(Restrictions.ilike(key, "%"+fieldsValues.get(key)+"%"));
					
				}else if (key.equals("startDate")) {
					if (fieldsValues.get(key) != null) {
						crt.add(Restrictions.ge(Vediotape.PROP_DATE_COMING, fieldsValues.get(key)));
						
					}

				}else if (key.equals("endDate")) {
					if (fieldsValues.get(key) != null) {
						crt.add(Restrictions.le(Vediotape.PROP_DATE_COMING, fieldsValues.get(key)));
						
					}
				}else{
					crt.add(Restrictions.eq(key, fieldsValues.get(key)));	
				}
				
			}
		}
		
		crt.setProjection(Projections.rowCount());
		
		return  Integer.parseInt(crt.uniqueResult().toString());
	}
	

}