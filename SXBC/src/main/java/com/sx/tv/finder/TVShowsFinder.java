package com.sx.tv.finder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.view.SearchTV;

public class TVShowsFinder {

	public static List<TVShow> findTVShows(SearchTV tv, int firstResult,
			int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null != tv.getId() && !"".equals(tv.getId())) {
			return findTVShowByID(tv.getId());
		}

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(
				"select a.* from "
						+ "(select distinct(t.id),t.actors,t.comments,t.count,t.directors,t.input_date,t.is_purchase,"
						+ "t.market_assessment,t.ratings,t.market_share,t.name,t.outline,t.publisher,t.ranking,"
						+ "t.reject_date,t.removed,t.screenwriters,t.version,t.company,t.inputter,t.progress,"
						+ "t.projector,t.status,t.theme,t.script_src,t.play_date,t.force_purchase "
						+ "from tvshow t ");

		boolean where = false;
		if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) { // dept
			sb.append("inner join dept_comments d on t.id = d.tvshow");
			where = false;
			if (tv.getRecommendChannel() != null) {
				sb.append(" where d.recommend_channel = :recommend_channel");
				params.put("recommend_channel", tv.getRecommendChannel()
						.getId());
				where = true;
			}
			if (tv.getRecommendLevel() != null) {
				if (where) {
					sb.append(" and d.recommend_level = :recommend_level");
					params.put("recommend_level", tv.getRecommendLevel()
							.getId());
				} else {
					sb.append(" where d.recommend_level = :recommend_level");
					params.put("recommend_level", tv.getRecommendLevel()
							.getId());
				}

			}
		}
		sb.append(")as a ");
		where = false;
		if (tv.getCopyrightFrom() != null || tv.getCopyrightTo() != null || tv.getCtcInputDateEnd()!=null || tv.getCtcInputDateStart() != null) { // contract
																			// query
			sb.append("inner join tvcontract b on a.id = b.tvshow ");
			where = false;

			if (tv.getCopyrightFrom() != null) {
				sb.append(" where b.copyright_start >= :copyright_start");
				params.put("copyright_start", tv.getCopyrightFrom());
				where = true;
			}
			if (tv.getCopyrightTo() != null) {
				if (where) {
					sb.append(" and b.copyright_end <= :copyright_end");
					params.put("copyright_end", tv.getCopyrightTo());
				} else {
					sb.append(" where b.copyright_end <= :copyright_end");
					params.put("copyright_end", tv.getCopyrightTo());
					where = true;
				}

			}
			
			if (tv.getCtcInputDateStart() != null) {
				if (where) {
					sb.append(" and b.input_date >= :ctcInput_date_start");
					params.put("ctcInput_date_start", tv.getCtcInputDateStart());
				} else {
					sb.append(" where b.input_date >= :ctcInput_date_start");
					params.put("ctcInput_date_start", tv.getCtcInputDateStart());
					where = true;
				}

			}
			
			if (tv.getCtcInputDateEnd() != null) {
				if (where) {
					sb.append(" and b.input_date <= :ctcInput_date_end");
					params.put("ctcInput_date_end", tv.getCtcInputDateEnd());
				} else {
					sb.append(" where b.input_date <= :ctcInput_date_end");
					params.put("ctcInput_date_end", tv.getCtcInputDateEnd());
					where = true;
				}

			}
			
			
			
		}

		if (where) {
			sb.append(" and a.removed = 0 ");
		} else {
			sb.append(" where a.removed = 0 ");
		}
		
		if (tv.getForcePurchase() == 1) {
			sb.append(" and a.force_purchase = 1");
		}
		
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" AND a.name LIKE :name");
			params.put("name", _name);
		}

		if (null != tv.getCompany()) {
			sb.append(" AND a.company = :company");
			params.put("company", tv.getCompany().getId());
		}

		if (null != tv.getProgress()) {
			sb.append(" AND a.progress = :progress");
			params.put("progress", tv.getProgress().getId());
		}

		if (null != tv.getTheme()) {
			sb.append(" AND a.theme = :theme ");
			params.put("theme", tv.getTheme().getId());
		}

		if (null != tv.getStatus()) {
			sb.append(" AND a.status = :status");
			params.put("status", tv.getStatus().getId());
		}

		if (null != tv.getProjector()) {
			sb.append(" AND a.projector = :projector");
			params.put("projector", tv.getProjector().getId());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append(" AND a.play_date >= :play_dateStart");
			params.put("play_dateStart", tv.getPlayDateStart());
		}

		if (null != tv.getPlayDateEnd()) {
			sb.append(" AND a.play_date <= :play_dateEnd");
			params.put("play_dateEnd", tv.getPlayDateEnd());
		}

		if (null != tv.getInputDateStart()) {
			sb.append(" AND a.input_date >= :input_dateStart");
			params.put("input_dateStart", tv.getInputDateStart());
		}

		if (null != tv.getInputDateEnd()) {
			sb.append(" AND a.input_date <= :input_dateEnd");
			params.put("input_dateEnd", tv.getInputDateEnd());
		}

		sb.append(" order by a." + orderby +" " + dir);
		EntityManager em = TVShow.entityManager();
		System.out.println(sb.toString());
		Query query = em.createNativeQuery(sb.toString(), TVShow.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		List<TVShow> resultList = query.setFirstResult(firstResult)
				.setMaxResults(size).getResultList();
		return resultList;
		// return findTVShowsByFields(tv, firstResult, size, orderby, dir);
	}

	public static List<TVContract> findDependsOnContractFields(SearchTV tv,
			int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVContract>();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(
				"select distinct(b.id),b.contract_no,b.copyright_end,b.copyright_start,"
						+ "b.extra_fee,file_agreement_date,b.file_by,b.file_by_agreement,b.file_date,"
						+ "b.input_agreement_date,b.input_date,b.inputter,b.inputter_agreement,owner,"
						+ "b.publish_form,b.recieve_agreement_date,b.recieve_agreement_owner,b.recieve_date,"
						+ "b.recieve_owner,b.show_date,b.total_price,b.version,b.channel,b.tvshow,"
						+ "b.price,b.ex_years,b.extension,b.tape_recive_date,b.tape_store_date,b.comments "
						+ "from tvshow t inner join tvcontract b on t.id = b.tvshow where t.removed = 0 and b.copyright_end is not null ");

		if (tv.getCopyrightFrom() != null) {
			sb.append(" and b.copyright_start >= :copyright_start ");
			params.put("copyright_start", tv.getCopyrightFrom());
		}
		if (tv.getCopyrightTo() != null) {
			sb.append(" and b.copyright_end <= :copyright_end ");
			params.put("copyright_end", tv.getCopyrightTo());
		}
		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append(" AND t.id = :id ");
			params.put("id", tv.getId());
		}
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" AND t.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append(" AND t.company = :company ");
			params.put("company", tv.getCompany());
		}
		if (null != tv.getProgress()) {
			sb.append(" AND t.progress = :progress ");
			params.put("progress", tv.getProgress());
		}
		if (null != tv.getTheme()) {
			sb.append(" AND t.theme = :theme ");
			params.put("theme", tv.getTheme());
		}
		if (null != tv.getStatus()) {
			sb.append(" AND t.status = :status ");
			params.put("status", tv.getStatus());
		}
		if (null != tv.getProjector()) {
			sb.append(" AND t.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}
		if (null != tv.getPlayChannel()) {
			sb.append("AND b.channel = :channel ");
			params.put("channel", tv.getPlayChannel());
		}
		if (null != tv.getPlayDateStart()) {
			sb.append(" AND t.play_date >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}
		if (null != tv.getPlayDateEnd()) {
			sb.append(" AND t.play_date <= :playDate ");
			params.put("playDate", tv.getPlayDateEnd());
		}

		if ("copyrightFrom".equalsIgnoreCase(orderby)) {
			sb.append("ORDER BY b.copyright_start " + dir);
		} else if ("copyrightEnd".equalsIgnoreCase(orderby)) {
			sb.append("ORDER BY b.copyright_end " + dir);
		} else if ("playChannel".equalsIgnoreCase(orderby)) {
			sb.append("ORDER BY b.channel " + dir);
		} else {
			sb.append("ORDER BY t." + orderby + " " + dir);
		}

		EntityManager em = TVShow.entityManager();
		Query query = em.createNativeQuery(sb.toString(), TVContract.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		List<TVContract> cts = query.setFirstResult(firstResult)
				.setMaxResults(size).getResultList();
		return cts;
	}

	public static long countTVShows(SearchTV tv)
			throws UnsupportedEncodingException {
		if (null != tv.getId() && !"".equals(tv.getId())) {
			return countTVShowByID(tv.getId());
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(
				"select count(a.id) from "
						+ "(select distinct(t.id),t.actors,t.comments,t.count,t.directors,t.input_date,t.is_purchase,"
						+ "t.market_assessment,t.ratings,t.market_share,t.name,t.outline,t.publisher,t.ranking,"
						+ "t.reject_date,t.removed,t.screenwriters,t.version,t.company,t.inputter,t.progress,"
						+ "t.projector,t.status,t.theme,t.script_src,t.play_date,t.force_purchase "
						+ "from tvshow t ");
		boolean where = false;
		if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) { // dept
			sb.append("inner join dept_comments d on t.id = d.tvshow");
			if (tv.getRecommendChannel() != null) {
				sb.append(" where d.recommend_channel = :recommend_channel");
				params.put("recommend_channel", tv.getRecommendChannel()
						.getId());
				where = true;
			}
			if (tv.getRecommendLevel() != null) {
				if (where) {
					sb.append(" and d.recommend_level = :recommend_level");
					params.put("recommend_level", tv.getRecommendLevel()
							.getId());
				} else {
					sb.append(" where d.recommend_level = :recommend_level");
					params.put("recommend_level", tv.getRecommendLevel()
							.getId());
				}

			}
		}
		sb.append(")as a ");
		where = false;
		if (tv.getCopyrightFrom() != null || tv.getCopyrightTo() != null || tv.getCtcInputDateEnd()!=null || tv.getCtcInputDateStart() != null) { // contract
																			// query
			sb.append("inner join tvcontract b on a.id = b.tvshow ");

			if (tv.getCopyrightFrom() != null) {
				sb.append(" where b.copyright_start >= :copyright_start");
				params.put("copyright_start", tv.getCopyrightFrom());
				where = true;
			}
			if (tv.getCopyrightTo() != null) {
				if (where) {
					sb.append(" and b.copyright_end <= :copyright_end");
					params.put("copyright_end", tv.getCopyrightTo());
				} else {
					sb.append(" where b.copyright_end <= :copyright_end");
					params.put("copyright_end", tv.getCopyrightTo());
					where = true;
				}

			}

			if (tv.getCtcInputDateStart() != null) {
				if (where) {
					sb.append(" and b.input_date >= :ctcInput_date_start");
					params.put("ctcInput_date_start", tv.getCtcInputDateStart());
				} else {
					sb.append(" where b.input_date >= :ctcInput_date_start");
					params.put("ctcInput_date_start", tv.getCtcInputDateStart());
					where = true;
				}

			}
			
			if (tv.getCtcInputDateEnd() != null) {
				if (where) {
					sb.append(" and b.input_date <= :ctcInput_date_end");
					params.put("ctcInput_date_end", tv.getCtcInputDateEnd());
				} else {
					sb.append(" where b.input_date <= :ctcInput_date_end");
					params.put("ctcInput_date_end", tv.getCtcInputDateEnd());
					where = true;
				}

			}
		}
		if (where) {
			sb.append(" and a.removed = 0 ");
		} else {
			sb.append(" where a.removed = 0 ");
		}
		
		if (tv.getForcePurchase() == 1) {
			sb.append(" and a.force_purchase = 1");
		}
		
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" AND a.name LIKE :name");
			params.put("name", _name);
		}

		if (null != tv.getCompany()) {
			sb.append(" AND a.company = :company");
			params.put("company", tv.getCompany().getId());
		}

		if (null != tv.getProgress()) {
			sb.append(" AND a.progress = :progress");
			params.put("progress", tv.getProgress().getId());
		}

		if (null != tv.getTheme()) {
			sb.append(" AND a.theme = :theme ");
			params.put("theme", tv.getTheme().getId());
		}

		if (null != tv.getStatus()) {
			sb.append(" AND a.status = :status");
			params.put("status", tv.getStatus().getId());
		}

		if (null != tv.getProjector()) {
			sb.append(" AND a.projector = :projector");
			params.put("projector", tv.getProjector().getId());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append(" AND a.play_date >= :play_dateStart");
			params.put("play_dateStart", tv.getPlayDateStart());
		}

		if (null != tv.getPlayDateEnd()) {
			sb.append(" AND a.play_date <= :play_dateEnd");
			params.put("play_dateEnd", tv.getPlayDateEnd());
		}

		if (null != tv.getInputDateStart()) {
			sb.append(" AND a.input_date >= :input_dateStart");
			params.put("input_dateStart", tv.getInputDateStart());
		}

		if (null != tv.getInputDateEnd()) {
			sb.append(" AND a.input_date <= :input_dateEnd");
			params.put("input_dateEnd", tv.getInputDateEnd());
		}

		System.out.println(sb.toString());
		EntityManager em = TVShow.entityManager();
		Query query = em.createNativeQuery(sb.toString());
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}

		java.math.BigInteger num = (BigInteger) query.getSingleResult();
		return num.longValue();
	}

	public static long countDependsOnContractFields(SearchTV tv)
			throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(
				"select count(*) from tvshow t inner join tvcontract b on t.id = b.tvshow where t.removed = 0 and b.copyright_end is not null ");

		if (tv.getCopyrightFrom() != null) {
			sb.append(" and b.copyright_start >= :copyright_start ");
			params.put("copyright_start", tv.getCopyrightFrom());
		}
		if (tv.getCopyrightTo() != null) {
			sb.append(" and b.copyright_end <= :copyright_end ");
			params.put("copyright_end", tv.getCopyrightTo());
		}
		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append(" AND t.id = :id ");
			params.put("id", tv.getId());
		}
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" AND t.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append(" AND t.company = :company ");
			params.put("company", tv.getCompany());
		}
		if (null != tv.getProgress()) {
			sb.append(" AND t.progress = :progress ");
			params.put("progress", tv.getProgress());
		}
		if (null != tv.getTheme()) {
			sb.append(" AND t.theme = :theme ");
			params.put("theme", tv.getTheme());
		}
		if (null != tv.getStatus()) {
			sb.append(" AND t.status = :status ");
			params.put("status", tv.getStatus());
		}
		if (null != tv.getProjector()) {
			sb.append(" AND t.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}
		if (null != tv.getPlayChannel()) {
			sb.append("AND b.channel = :channel ");
			params.put("channel", tv.getPlayChannel());
		}
		if (null != tv.getPlayDateStart()) {
			sb.append(" AND t.play_date >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}
		if (null != tv.getPlayDateEnd()) {
			sb.append(" AND t.play_date <= :playDate ");
			params.put("playDate", tv.getPlayDateEnd());
		}

		EntityManager em = TVShow.entityManager();
		Query query = em.createNativeQuery(sb.toString());
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}

		java.math.BigInteger num = (BigInteger) query.getSingleResult();
		return num.longValue();
	}

	public static List<TVShow> findTVShowsByFields(SearchTV tv,
			int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM TVShow AS o WHERE o.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND o.name LIKE :name ");
			params.put("name", _name);
		}

		if (null != tv.getCompany()) {
			sb.append("AND o.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			sb.append("AND o.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			sb.append("AND o.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND o.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			sb.append("AND o.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			sb.append("AND o.projector = :projector ");
			params.put("projector", tv.getProjector());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.playDate >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.playDate <= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}

		if (null != tv.getIsPurchase()) {
			sb.append("AND o.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}
		sb.append("ORDER BY o." + orderby + " " + dir);

		Query query = em.createQuery(sb.toString(), TVShow.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(firstResult).setMaxResults(size)
				.getResultList();
	}

	public static String convertLikeString(String str) {
		if (str.charAt(0) != '%') {
			str = "%" + str;
		}
		if (str.charAt(str.length() - 1) != '%') {
			str = str + "%";
		}
		return str;
	}

	public static long countTVShowsByFields(SearchTV tv)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return 0;
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT COUNT(o) FROM TVShow AS o WHERE o.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND o.name LIKE :name ");
			params.put("name", _name);
		}

		if (null != tv.getCompany()) {
			sb.append("AND o.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			sb.append("AND o.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			sb.append("AND o.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND o.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			sb.append("AND o.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			sb.append("AND o.projector = :projector ");
			params.put("projector", tv.getProjector());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.playDate >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.playDate <= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}

		if (null != tv.getIsPurchase()) {
			sb.append("AND o.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}
		Query query = em.createQuery(sb.toString(), Long.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		return (Long) query.getSingleResult();
	}

	public static List<TVShow> findDependsOnRecommendChannel(SearchTV tv,
			int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = DeptComments.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM DeptComments AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tv.getRecommendChannel()) {
			sb.append("AND o.recommendChannel LIKE :recommendChannel ");
			params.put("recommendChannel", tv.getRecommendChannel());
		}

		if (null != tv.getRecommendLevel()) {
			sb.append("AND o.recommendLevel LIKE :recommendLevel ");
			params.put("recommendLevel", tv.getRecommendLevel());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND o.tvshow.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append("AND o.tvshow.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			sb.append("AND o.tvshow.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			sb.append("AND o.tvshow.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND o.tvshow.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			sb.append("AND o.tvshow.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			sb.append("AND o.tvshow.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getIsPurchase()) {
			sb.append("AND o.tvshow.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}

		sb.append("ORDER BY o.tvshow." + orderby + " " + dir);

		Query query = em.createQuery(sb.toString(), DeptComments.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}

		List<DeptComments> depts = query.setFirstResult(firstResult)
				.setMaxResults(size).getResultList();
		if (null != depts && !depts.isEmpty()) {
			List<TVShow> data = new ArrayList<TVShow>();
			for (DeptComments d : depts) {
				data.add(d.getTvshow());
			}
			return data;
		}
		return new ArrayList<TVShow>();

	}

	public static long countDependsOnRecommendChannel(SearchTV tv)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return 0;
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT COUNT(o) FROM DeptComments AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tv.getRecommendChannel()) {
			sb.append("AND o.recommendChannel LIKE :recommendChannel ");
			params.put("recommendChannel", tv.getRecommendChannel());
		}

		if (null != tv.getRecommendLevel()) {
			sb.append("AND o.recommendLevel LIKE :recommendLevel ");
			params.put("recommendLevel", tv.getRecommendLevel());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND o.tvshow.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append("AND o.tvshow.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			sb.append("AND o.tvshow.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			sb.append("AND o.tvshow.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND o.tvshow.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			sb.append("AND o.tvshow.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			sb.append("AND o.tvshow.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getIsPurchase()) {
			sb.append("AND o.tvshow.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}

		Query query = em.createQuery(sb.toString(), Long.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public static List<TVShow> findTVShowByID(Long id) {
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.id = :id ");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static long countTVShowByID(Long id) {
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT COUNT(o) FROM TVShow AS o WHERE o.removed = 0 AND o.id = :id");
		Query query = em.createQuery(sb.toString(), Long.class);
		query.setParameter("id", id);
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public static List<TVShow> findTVShowsByName(String name)
			throws UnsupportedEncodingException {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		String tname = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		String _name = convertLikeString(tname.replace('*', '%'));
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.name like :name");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		query.setParameter("name", _name);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<TVShow> findAllTVShows() {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.name like :name");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<TVShow> findTVShowsByStatus(int firstResult, int size,
			String orderby, String dir, int[] status) {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.status in (");
		for (int i = 0; i < status.length; i++) {
			if (i != 0 && i < status.length) {
				sb.append(",");
			}
			sb.append(" " + status[i]);
		}
		sb.append(") ORDER BY o." + orderby + " " + dir);
		Query query = em.createQuery(sb.toString(), TVShow.class)
				.setFirstResult(firstResult).setMaxResults(size);
		return query.getResultList();
	}

	public static long countAllTVShowsByStatus(int... status) {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer(
				"SELECT COUNT(o) FROM TVShow AS o WHERE o.removed = 0 AND o.status in (");
		for (int i = 0; i < status.length; i++) {
			if (i != 0 && i < status.length) {
				sb.append(",");
			}
			sb.append(" " + status[i]);
		}
		sb.append(")");
		Query query = em.createQuery(sb.toString(), Long.class);

		return (Long) query.getSingleResult();
	}

	public static List<TVShow> findTVShowEntries(int firstResult, int maxResults) {
		return TVShow
				.entityManager()
				.createQuery("SELECT o FROM TVShow o WHERE o.removed = 0",
						TVShow.class).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
	}

	public static long countTVShows() {
		return TVShow
				.entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM TVShow o WHERE o.removed = 0",
						Long.class).getSingleResult();
	}

	public static List<PlayInfo> findDependsOnPlayInfo(SearchTV tv,
			int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<PlayInfo>();
		}
		EntityManager em = PlayInfo.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM PlayInfo AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND o.tvshow.id = :id ");
			params.put("id", tv.getId());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND o.tvshow.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append("AND o.tvshow.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getProgress()) {
			sb.append("AND o.tvshow.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND o.tvshow.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			sb.append("AND o.tvshow.status = :status ");
			params.put("status", tv.getStatus());
			int sid = tv.getStatus().getId();
			int round = 0;
			if (sid == StatusUtil.ER_LUN_DAI_BO
					|| sid == StatusUtil.ER_LUN_YI_BO) {
				round = 2;
			}
			if (sid == StatusUtil.SAN_LUN_YI_BO
					|| sid == StatusUtil.SAN_LUN_DAI_BO) {
				round = 3;
			}
			if (sid == StatusUtil.SAN_LUN_HOU_DAI_BO
					|| sid == StatusUtil.SAN_LUN_HOU_YI_BO) {
				round = 4;
			}
		}

		if (null != tv.getProjector()) {
			sb.append("AND o.tvshow.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getPlayChannel()) {
			sb.append("AND o.playChannel = :playChannel ");
			params.put("playChannel", tv.getPlayChannel());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.playDate >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}

		if (null != tv.getPlayDateEnd()) {
			sb.append("AND o.playDate <= :playDate ");
			params.put("playDate", tv.getPlayDateEnd());
		}

		Query query = em.createQuery(sb.toString(), PlayInfo.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		List<PlayInfo> infos = query.setFirstResult(firstResult)
				.setMaxResults(size).getResultList();
		return infos;
	}

	public static long countDependsOnPlayInfo(SearchTV tv)
			throws UnsupportedEncodingException {

		EntityManager em = PlayInfo.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT COUNT(o) FROM PlayInfo AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND o.tvshow.id = :id ");
			params.put("id", tv.getId());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND o.tvshow.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append("AND o.tvshow.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getProgress()) {
			sb.append("AND o.tvshow.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND o.tvshow.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			sb.append("AND o.tvshow.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			sb.append("AND o.tvshow.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getPlayChannel()) {
			sb.append("AND o.playChannel = :playChannel ");
			params.put("playChannel", tv.getPlayChannel());
		}

		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.playDate >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}

		if (null != tv.getPlayDateEnd()) {
			sb.append("AND o.playDate <= :playDate ");
			params.put("playDate", tv.getPlayDateEnd());
		}

		Query query = em.createQuery(sb.toString(), Long.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}

		Long count = (Long) query.getSingleResult();

		return count;
	}
}
