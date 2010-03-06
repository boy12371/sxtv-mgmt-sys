package com.vms.db.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vms.common.DaoUtils;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IPlayorderDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class PlayorderDAO extends com.vms.db.dao.BaseRootDAO  implements IPlayorderDAO {

	private Class clz = com.vms.db.bean.Playorder.class;
	
	public Date getFirstArrangedDate() throws Exception {
		Criteria crt = this.getCriteria(clz);
		Date min = (Date)crt.setProjection( Projections.projectionList().add(Projections.min(Playorder.PROP_PLAY_DATE))).uniqueResult();
		return min;
	}

	@Override
	public void deletePlayorder(List<Playorder> pos, int userID) throws Exception {
		for(Playorder po: pos){
			deleteObject(po);
			//update play change log
			Playchangelog plog = new Playchangelog();
			plog.setVedioID(po.getVedioID());
			plog.setOperation("移除");
			plog.setAuditor(new User(userID));
			plog.setFromDate(po.getPlayDate());
			plog.setDate(new Date());
			this.saveObject(plog);
			
			Vediotape tape = (Vediotape)this.getObject(Vediotape.class, po.getVedioID().getId());
			tape.setStatus(new Status(5));
			this.updateObject(tape);
		}
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
	public List<Playorder> findPlayorderBetweenDateWithFeedback(Date startDate, Date endDate) throws Exception {
		Criteria crt = this.getCriteria(Playorder.class);
		crt.add(Restrictions.between(Playorder.PROP_PLAY_DATE, startDate, endDate))
		.createCriteria(Playorder.PROP_VEDIO_I_D).add(Restrictions.eq(Vediotape.PROP_STATUS, new Status(9)));
		return (List<Playorder>)crt.list();
	}

	@Override
	public void savePlayorder(List<Playorder> pos, int userID) throws Exception {
		for(Playorder p: pos){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put(Playorder.PROP_VEDIO_I_D, p.getVedioID());
//			conditions.put(Playorder.PROP_AUDITOR, p.getAuditor());
			//update play change log
			Playchangelog plog = new Playchangelog();
			plog.setVedioID(p.getVedioID());
			plog.setAuditor(new User(userID));
			plog.setToDate(p.getPlayDate());
			plog.setDate(new Date());
			plog.setOperation("加入");
			
			List<Playorder> temp = findObjectByFields(Playorder.class, conditions, -1, -1, Playorder.PROP_ID, true);
			if(null != temp && 0 != temp.size()){
				p.setId(temp.get(0).getId());
				plog.setFromDate(temp.get(0).getPlayDate());
				plog.setOperation("更新");
			}
			this.getHibernateTemplate().merge(p);
			this.saveObject(plog);
			
			Vediotape tape = (Vediotape)this.getObject(Vediotape.class, p.getVedioID().getId());
			tape.setStatus(new Status(6));
			this.updateObject(tape);
		}
	}

}