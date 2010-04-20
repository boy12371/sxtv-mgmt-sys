package com.vms.db.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vms.common.DaoUtils;
import com.vms.common.SearchCondition;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Scorelevel;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IPlayorderDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class PlayorderDAO extends com.vms.db.dao.BaseRootDAO implements
		IPlayorderDAO {

	private Class clz = com.vms.db.bean.Playorder.class;

	public Date getFirstArrangedDate() throws Exception {
		Criteria crt = this.getCriteria(clz);
		Date min = (Date) crt.setProjection(
				Projections.projectionList().add(
						Projections.min(Playorder.PROP_PLAY_DATE)))
				.uniqueResult();
		return min;
	}

	@Override
	public void deletePlayorder(List<Playorder> pos, int userID)
			throws Exception {
		for (Playorder po : pos) {
			deleteObject(po);
			// update play change log
			Playchangelog plog = new Playchangelog();
			plog.setVedioID(po.getVedioID());
			plog.setOperation("移除");
			plog.setAuditor(new User(userID));
			plog.setFromDate(po.getPlayDate());
			plog.setDate(new Date());
			this.saveObject(plog);

			Vediotape tape = (Vediotape) this.getObject(Vediotape.class, po
					.getVedioID().getId());
			tape.setStatus(new Status(5));
			this.updateObject(tape);
		}
	}

	public List<Playorder> findMonthPlayOrder(Date startTime, Date endTime,
			int startIndex, int endIndex, boolean ascending) throws Exception {
		Criteria crt = this.getCriteria(Playorder.class);

		if (startTime != null) {
			crt.add(Restrictions.ge(Playorder.PROP_PLAY_DATE, startTime));
		}

		if (endTime != null) {
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

	public class scoreComparator implements Comparator<Playorder> {
		public int compare(Playorder o1, Playorder o2) {
			float s1 = o1.getVedioID().getAudienceRating();
			float s2 = o2.getVedioID().getAudienceRating();
			return s1 == s2 ? 0 : (s1 < s2 ? 1 : -1);
		}
	}

	@Override
	public List<Playorder> findPlayorderBetweenDateWithFeedback(Date startDate,
			Date endDate, int selSubject) throws Exception {
		Subject subject = new Subject(selSubject);
		Criteria temp = this.getCriteria(Playorder.class);
		Criteria crt = temp.add(
				Restrictions.between(Playorder.PROP_PLAY_DATE, startDate,
						endDate)).createCriteria(Playorder.PROP_VEDIO);
		crt.add(Restrictions.eq(Vediotape.PROP_STATUS, new Status(9)));
		if (selSubject > 0) {
			crt.add(Restrictions.eq(Vediotape.PROP_SUBJECT, subject));
		}

		List<Playorder> vList = crt.list();
		Comparator<Playorder> comparator = new scoreComparator();
		Collections.sort(vList, comparator);

		List<Scorelevel> levels = this.findObjectByFields(Scorelevel.class,
				null, -1, -1, Scorelevel.PROP_LEVEL, true);
		for (int i = 0; i < vList.size(); i++) {
			Vediotape tape = vList.get(i).getVedioID();
			for (int j = 0; j < levels.size(); j++) {
				Scorelevel level = levels.get(j);
				if ((i + 1) >= level.getStart() && (i + 1) <= level.getEnd()) {
					tape.setScore(level.getLevelScore());
					break;
				}
			}
		}
		return vList;
	}

	@Override
	public void savePlayorder(List<Playorder> pos, int userID) throws Exception {
		for (Playorder p : pos) {
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put(Playorder.PROP_VEDIO, p.getVedioID());
			// conditions.put(Playorder.PROP_AUDITOR, p.getAuditor());
			// update play change log
			Playchangelog plog = new Playchangelog();
			plog.setVedioID(p.getVedioID());
			plog.setAuditor(new User(userID));
			plog.setToDate(p.getPlayDate());
			plog.setDate(new Date());
			plog.setOperation("加入");

			List<Playorder> temp = findObjectByFields(Playorder.class,
					conditions, -1, -1, Playorder.PROP_ID, true);
			if (null != temp && 0 != temp.size()) {
				p.setId(temp.get(0).getId());
				plog.setFromDate(temp.get(0).getPlayDate());
				plog.setOperation("更新");
			}
			this.getHibernateTemplate().merge(p);
			this.saveObject(plog);

			Vediotape tape = (Vediotape) this.getObject(Vediotape.class, p
					.getVedioID().getId());
			tape.setStatus(new Status(6));
			this.updateObject(tape);
		}
	}

	@Override
	public List<Playorder> findPlayorderByDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startTime = cal.getTime();

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endTime = cal.getTime();

		Criteria crt = this.getCriteria(Playorder.class);

		crt.add(Restrictions.ge(Playorder.PROP_PLAY_DATE, startTime));
		crt.add(Restrictions.le(Playorder.PROP_PLAY_DATE, endTime));
		return (List<Playorder>) crt.list();

	}

	@Override
	public List<Vediotape> findVideosByRateOrder(SearchCondition condition)
			throws ParseException {
		StringBuffer queryString = new StringBuffer(
				"FROM Playorder o WHERE o.playDate between :startDate AND :endDate");
		if (condition.getCompany() != null
				&& condition.getCompany().getId() != 0) {
			queryString.append(" AND o.vedioID.companyID.id = :company ");
		}
		if (condition.getTopic() != null && condition.getTopic().getId() != 0) {
			queryString.append(" AND o.vedioID.topic.id = :topic ");
		}
		if (condition.getSubject() != null
				&& condition.getSubject().getId() != 0) {
			queryString.append(" AND o.vedioID.subject.id = :subject ");
		}
		queryString.append(" ORDER BY o.vedioID.audienceRating DESC");
		Query query = this.getQuery(queryString.toString()).setDate(
				"startDate", condition.getStartingDate()).setDate("endDate",
				condition.getEndingDate());

		if (condition.getCompany() != null
				&& condition.getCompany().getId() != 0) {
			query.setInteger("company", condition.getCompany().getId());
		}
		if (condition.getTopic() != null && condition.getTopic().getId() != 0) {
			query.setInteger("topic", condition.getTopic().getId());
		}
		if (condition.getSubject() != null
				&& condition.getSubject().getId() != 0) {
			query.setInteger("subject", condition.getSubject().getId());
		}
		List<Playorder> orders = query.list();
		List<Vediotape> vs = new ArrayList<Vediotape>();
		if (orders != null && !orders.isEmpty()) {
			for (Playorder playorder : orders) {
				vs.add(playorder.getVedioID());
			}
		}
		return vs;
	}
}