package com.sx.tv.finder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.TVShow;
import com.sx.tv.view.SearchTV;

public class TVShowsFinder {

	public static List<TVShow> findTVShows(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null != tv.getId() && !"".equals(tv.getId())) {
			return findTVShowByID(tv.getId());
		}
		if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) {
			return findTVShowsOnOtherField(tv, firstResult, size, orderby, dir);
		}
		return findTVShowsByFields(tv, firstResult, size, orderby, dir);
	}

	public static long countTVShows(SearchTV tv) throws UnsupportedEncodingException {
		if (null != tv.getId() && !"".equals(tv.getId())) {
			return countTVShowByID(tv.getId());
		}
		if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) {
			return countTVShowsOnOtherField(tv);
		}
		return countTVShowsByFields(tv);
	}

	public static List<TVShow> findTVShowsByFields(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		Map<String, Object> params = new HashMap<String, Object>();
		boolean added = false;
		boolean where = false;

		if (null != tv.getName() && !"".equals(tv.getName())) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" o.name LIKE :name ");
			added = true;
			params.put("name", _name);
		}

		if (null != tv.getCompany()) {
			if (!where) {
				sb.append("WHERE");
				where = true;

			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");

			}
			added = true;
			sb.append(" o.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.projector = :projector ");
			params.put("projector", tv.getProjector());
		}

		if (null != tv.getInputDate()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.inputDate = :inputDate ");
			params.put("inputDate", tv.getInputDate());
		}

		if (null != tv.getIsPurchase()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}
		sb.append("ORDER BY o." + orderby + " " + dir);

		Query query = em.createQuery(sb.toString(), TVShow.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(firstResult).setMaxResults(size).getResultList();
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

	public static long countTVShowsByFields(SearchTV tv) throws UnsupportedEncodingException {
		if (null == tv) {
			return 0;
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM TVShow AS o ");
		Map<String, Object> params = new HashMap<String, Object>();
		boolean added = false;
		boolean where = false;
		if (null != tv.getName() && !"".equals(tv.getName())) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" o.name LIKE :name ");
			added = true;
			params.put("name", _name);
		}

		if (null != tv.getCompany()) {
			if (!where) {
				sb.append("WHERE");
				where = true;

			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");

			}
			added = true;
			sb.append(" o.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getInputDate()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.inputDate LIKE :inputDate ");
			params.put("inputDate", tv.getInputDate());
		}

		if (null != tv.getIsPurchase()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}
		Query query = em.createQuery(sb.toString(), Long.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		return (Long) query.getSingleResult();
	}

	public static List<TVShow> findTVShowsOnOtherField(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT o FROM DeptComments AS o ");
		Map<String, Object> params = new HashMap<String, Object>();
		boolean added = false;
		boolean where = false;
		if (null != tv.getRecommendChannel()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			sb.append(" o.recommendChannel LIKE :recommendChannel ");
			added = true;
			params.put("recommendChannel", tv.getRecommendChannel());
		}

		if (null != tv.getRecommendLevel()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			sb.append(" o.recommendLevel LIKE :recommendLevel ");
			added = true;
			params.put("recommendLevel", tv.getRecommendLevel());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" o.tvshow.name LIKE :name ");
			added = true;
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			if (!where) {
				sb.append("WHERE");
				where = true;

			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");

			}
			added = true;
			sb.append(" o.tvshow.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getInputDate()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.inputDate = :inputDate ");
			params.put("inputDate", tv.getInputDate());
		}

		if (null != tv.getIsPurchase()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.isPurchase = :isPurchase ");
			params.put("isPurchase", tv.getIsPurchase());
		}

		sb.append("ORDER BY o.tvshow." + orderby + " " + dir);

		Query query = em.createQuery(sb.toString(), DeptComments.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}

		List<DeptComments> depts = query.setFirstResult(firstResult).setMaxResults(size).getResultList();
		if (null != depts && !depts.isEmpty()) {
			List<TVShow> data = new ArrayList<TVShow>();
			for (DeptComments d : depts) {
				data.add(d.getTvshow());
			}
			return data;
		}
		return new ArrayList<TVShow>();

	}

	public static long countTVShowsOnOtherField(SearchTV tv) throws UnsupportedEncodingException {
		if (null == tv) {
			return 0;
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM DeptComments AS o ");
		Map<String, Object> params = new HashMap<String, Object>();
		boolean added = false;
		boolean where = false;
		if (null != tv.getRecommendChannel()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			sb.append(" o.recommendChannel LIKE :recommendChannel ");
			added = true;
			params.put("recommendChannel", tv.getRecommendChannel());
		}

		if (null != tv.getRecommendLevel()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			sb.append(" o.recommendLevel LIKE :recommendLevel ");
			added = true;
			params.put("recommendLevel", tv.getRecommendLevel());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND");
			}
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append(" o.tvshow.name LIKE :name ");
			added = true;
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			if (!where) {
				sb.append("WHERE");
				where = true;

			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getPriceRange() && !"".equals(tv.getPriceRange())) {
			String pr = convertLikeString(tv.getPriceRange());
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.priceRange LIKE :priceRange ");
			params.put("priceRange", pr);
		}

		if (null != tv.getProgress()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");

			}
			added = true;
			sb.append(" o.tvshow.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		if (null != tv.getStatus()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.status = :status ");
			params.put("status", tv.getStatus());
		}

		if (null != tv.getProjector()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getInputDate()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.inputDate = :inputDate ");
			params.put("inputDate", tv.getInputDate());
		}

		if (null != tv.getIsPurchase()) {
			if (!where) {
				sb.append("WHERE");
				where = true;
			}
			if (added) {
				sb.append("AND ");
			}
			added = true;
			sb.append(" o.tvshow.isPurchase = :isPurchase ");
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
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.id = :id");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static long countTVShowByID(Long id) {
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM TVShow AS o WHERE o.id = :id");
		Query query = em.createQuery(sb.toString(), Long.class);
		query.setParameter("id", id);
		return (Long) query.getSingleResult();
	}

}
