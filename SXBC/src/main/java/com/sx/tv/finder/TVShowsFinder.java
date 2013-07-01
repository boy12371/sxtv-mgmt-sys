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

	public static List<TVShow> findTVShows(SearchTV tv, int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
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

	public static List<TVShow> findTVShowsByFields(SearchTV tv, int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o where o.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
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

		if (null != tv.getInputDate()) {
			sb.append("AND o.inputDate = :inputDate ");
			params.put("inputDate", tv.getInputDate());
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
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM TVShow AS o WHERE o.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
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
			sb.append("AND o.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (null != tv.getInputDate()) {
			sb.append("AND o.inputDate LIKE :inputDate ");
			params.put("inputDate", tv.getInputDate());
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

	public static List<TVShow> findTVShowsOnOtherField(SearchTV tv, int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT o FROM DeptComments AS o WHERE o.removed = 0 ");
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
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
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

		if (null != tv.getInputDate()) {
			sb.append("AND o.tvshow.inputDate = :inputDate ");
			params.put("inputDate", tv.getInputDate());
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
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM DeptComments AS o WHERE o.removed = 0 ");
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
			String tname = new String(tv.getName().getBytes("ISO-8859-1"), "UTF-8");
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

		if (null != tv.getInputDate()) {
			sb.append("AND o.tvshow.inputDate = :inputDate ");
			params.put("inputDate", tv.getInputDate());
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
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.id = :id ");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static long countTVShowByID(Long id) {
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM TVShow AS o WHERE o.removed = 0 AND o.id = :id");
		Query query = em.createQuery(sb.toString(), Long.class);
		query.setParameter("id", id);
		return (Long) query.getSingleResult();
	}

	public static List<TVShow> findTVShowsByName(String name) throws UnsupportedEncodingException {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		String tname = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		String _name = convertLikeString(tname.replace('*', '%'));
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.name like :name");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		query.setParameter("name", _name);
		return query.getResultList();
	}

	public static List<TVShow> findAllTVShows() {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.name like :name");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		return query.getResultList();
	}

	public static List<TVShow> findTVShowEntries(int firstResult, int maxResults) {
		return TVShow.entityManager().createQuery("SELECT o FROM TVShow o WHERE o.removed = 0", TVShow.class).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
	}

	public static long countTVShows() {
		return TVShow.entityManager().createQuery("SELECT COUNT(o) FROM TVShow o WHERE o.removed = 0", Long.class).getSingleResult();
	}
}
