package com.vms.db.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vms.common.DaoUtils;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IPlayorderDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class PlayorderDAO extends com.vms.db.dao.BaseRootDAO  implements IPlayorderDAO {

	private Class clz = com.vms.db.bean.Playorder.class;

	@Override
	public void deletePlayorder(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public List<Playorder> findMonthPlayOrder(Date startTime, Date endTime, int startIndex, int endIndex, boolean ascending) throws Exception{
		Criteria crt = this.getCriteria(Playorder.class);
		
		if(startTime != null){
			crt.add(Restrictions.ge(Playorder.PROP_PLAY_DATE, startTime));
		}
		
		if(endTime != null){
			crt.add(Restrictions.le(Playorder.PROP_PLAY_DATE, endTime));
		}

		Order order = DaoUtils.getOrder(Playorder.PROP_PLAY_DATE, ascending);
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
	public List<Playorder> findPlayorderByMonth(Date date) throws Exception {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		cal.add(Calendar.MONTH, -1);
		Date startDate = cal.getTime();
		
		cal.add(Calendar.MONTH, 1);
		Date endDate = cal.getTime();
		
		Criteria crt = this.getCriteria(Vediotape.class);
		crt.add(Restrictions.ge(Playorder.PROP_PLAY_DATE, startDate));
		crt.add(Restrictions.le(Playorder.PROP_PLAY_DATE, endDate));
		return (List<Playorder>)crt.list();
	}

	@Override
	public void savePlayorder(List<Playorder> orders) throws Exception {
		// TODO Auto-generated method stub
		for (Playorder playorder : orders) {
			this.getHibernateTemplate().save(playorder);
		}
		
	}

	@Override
	public boolean updatePlayOrder(int orderID, Date fromDate, Date toDate,
			int userID) throws Exception {
		// TODO Auto-generated method stub
		String hqlString ="update Playorder order set order.playDate =?, order.arrangeDatewhere=? where order.id=?";
		int result  = this.getHibernateTemplate().bulkUpdate(hqlString, new Object[]{toDate,new Date(),orderID});
		return result != 0;
		
	}
}