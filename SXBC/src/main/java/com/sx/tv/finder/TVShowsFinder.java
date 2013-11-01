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
import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.view.SearchTV;

public class TVShowsFinder {

	public static List<TVShow> findTVShows(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null != tv.getId() && !"".equals(tv.getId())) {
			return findTVShowByID(tv.getId());
		}
		if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) {
			return findDependsOnRecommendChannel(tv, firstResult, size, orderby, dir);
		}

//		if (tv.getPlayChannel() != null || tv.getCopyrightFrom() != null || tv.getCopyrightTo() != null || (tv.getContractNo() != null && !tv.getContractNo().equals(""))
//				|| tv.getPlayDateStart() != null || tv.getPlayDateEnd() != null) {
//			int status = tv.getStatus() == null ? -1 : tv.getStatus().getId();
//			if (status == StatusUtil.YI_BO || status == StatusUtil.DAI_BO) {
//				// search tvshow from TVContract in table playinfo
//				return findDependsOnContractFields(tv, firstResult, size, orderby, dir);
//			} else if (status == StatusUtil.ER_LUN_DAI_BO || status == StatusUtil.ER_LUN_YI_BO || status == StatusUtil.SAN_LUN_YI_BO || status == StatusUtil.SAN_LUN_DAI_BO
//					|| status == StatusUtil.SAN_LUN_HOU_DAI_BO || status == StatusUtil.SAN_LUN_HOU_YI_BO) {
//				// search tvshow from Playinfo in table playinfo
//
//			} else {
//				// search tvshow from TVShow
//			}
//		}

		return findTVShowsByFields(tv, firstResult, size, orderby, dir);
	}

	public static List<TVContract> findDependsOnContractFields(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVContract>();
		}
		EntityManager em = TVContract.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM TVContract AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND o.tvshow.id = :id ");
			params.put("id", tv.getId());
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
			sb.append("AND o.channel = :channel ");
			params.put("channel", tv.getPlayChannel());
		}
		if (null != tv.getCopyrightFrom()) {
			sb.append("AND o.copyrightStart >= :copyrightStart ");
			params.put("copyrightStart", tv.getCopyrightFrom());
		}
		if (null != tv.getCopyrightTo()) {
			sb.append("AND o.copyrightEnd <= :copyrightEnd ");
			params.put("copyrightEnd", tv.getCopyrightTo());
		}
		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.tvshow.playDate >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}
		if (null != tv.getPlayDateEnd()) {
			sb.append("AND o.tvshow.playDate <= :playDate ");
			params.put("playDate", tv.getPlayDateEnd());
		}
		if (null != tv.getContractNo() && !"".equals(tv.getContractNo())) {
			sb.append("AND o.contractNo = :contractNo ");
			params.put("contractNo", tv.getContractNo());
		}
		sb.append("ORDER BY o.tvshow." + orderby + " " + dir);
		Query query = em.createQuery(sb.toString(), TVContract.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		List<TVContract> cts = query.setFirstResult(firstResult).setMaxResults(size).getResultList();
		return cts;
	}

	public static long countTVShows(SearchTV tv) throws UnsupportedEncodingException {
		if (null != tv.getId() && !"".equals(tv.getId())) {
			return countTVShowByID(tv.getId());
		}
		if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) {
			return countDependsOnRecommendChannel(tv);
		}

		if (tv.getPlayChannel() != null || tv.getCopyrightTo() != null || tv.getCopyrightTo() != null || (tv.getContractNo() != null && !tv.getContractNo().equals(""))) {
			return countDependsOnContractFields(tv);
		}
		return countTVShowsByFields(tv);
	}

	public static long countDependsOnContractFields(SearchTV tv) throws UnsupportedEncodingException {
		if (null == tv) {
			return 0;
		}
		EntityManager em = TVContract.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM TVContract AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND o.tvshow.id = :id ");
			params.put("id", tv.getId());
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
			sb.append("AND o.channel = :channel ");
			params.put("channel", tv.getPlayChannel());
		}
		if (null != tv.getCopyrightFrom()) {
			sb.append("AND o.copyrightStart >= :copyrightStart ");
			params.put("copyrightStart", tv.getCopyrightFrom());
		}
		if (null != tv.getCopyrightTo()) {
			sb.append("AND o.copyrightEnd <= :copyrightEnd ");
			params.put("copyrightEnd", tv.getCopyrightTo());
		}
		if (null != tv.getPlayDateStart()) {
			sb.append("AND o.tvshow.playDate >= :playDate ");
			params.put("playDate", tv.getPlayDateStart());
		}
		if (null != tv.getPlayDateEnd()) {
			sb.append("AND o.tvshow.playDate <= :playDate ");
			params.put("playDate", tv.getPlayDateEnd());
		}
		if (null != tv.getContractNo() && !"".equals(tv.getContractNo())) {
			sb.append("AND o.contractNo = :contractNo ");
			params.put("contractNo", tv.getContractNo());
		}
		Query query = em.createQuery(sb.toString(), Long.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		Long count = (Long) query.getSingleResult();
		return count;
	}

	public static List<TVShow> findTVShowsByFields(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.removed = 0 ");
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

	public static List<TVShow> findDependsOnRecommendChannel(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVShow>();
		}
		EntityManager em = DeptComments.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT o FROM DeptComments AS o WHERE o.tvshow.removed = 0 ");
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

	public static long countDependsOnRecommendChannel(SearchTV tv) throws UnsupportedEncodingException {
		if (null == tv) {
			return 0;
		}
		EntityManager em = TVShow.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM DeptComments AS o WHERE o.tvshow.removed = 0 ");
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public static List<TVShow> findAllTVShows() {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.name like :name");
		Query query = em.createQuery(sb.toString(), TVShow.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<TVShow> findTVShowsByStatus(int firstResult, int size, String orderby, String dir, int[] status) {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.removed = 0 AND o.status in (");
		for (int i = 0; i < status.length; i++) {
			if (i != 0 && i < status.length) {
				sb.append(",");
			}
			sb.append(" " + status[i]);
		}
		sb.append(") ORDER BY o." + orderby + " " + dir);
		Query query = em.createQuery(sb.toString(), TVShow.class).setFirstResult(firstResult).setMaxResults(size);
		return query.getResultList();
	}

	public static long countAllTVShowsByStatus(int... status) {
		EntityManager em = TVShow.entityManager();
		// StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o ");
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM TVShow AS o WHERE o.removed = 0 AND o.status in (");
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
		return TVShow.entityManager().createQuery("SELECT o FROM TVShow o WHERE o.removed = 0", TVShow.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	public static long countTVShows() {
		return TVShow.entityManager().createQuery("SELECT COUNT(o) FROM TVShow o WHERE o.removed = 0", Long.class).getSingleResult();
	}
	
	
	public static List<PlayInfo> findDependsOnPlayInfo(SearchTV tv, int firstResult, int size, String orderby, String dir) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<PlayInfo>();
		}
		EntityManager em = PlayInfo.entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM PlayInfo AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND o.tvshow.id = :id ");
			params.put("id", tv.getId());
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
			if (sid == StatusUtil.ER_LUN_DAI_BO || sid == StatusUtil.ER_LUN_YI_BO) {
				round = 2;
			}
			if (sid == StatusUtil.SAN_LUN_YI_BO || sid == StatusUtil.SAN_LUN_DAI_BO) {
				round = 3;
			}
			if (sid == StatusUtil.SAN_LUN_HOU_DAI_BO || sid == StatusUtil.SAN_LUN_HOU_YI_BO) {
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
		List<PlayInfo> infos = query.setFirstResult(firstResult).setMaxResults(size).getResultList();
		return infos;
	}

	public static long countDependsOnPlayInfo(SearchTV tv) throws UnsupportedEncodingException {
		
		EntityManager em = PlayInfo.entityManager();
		StringBuffer sb = new StringBuffer("SELECT COUNT(o) FROM PlayInfo AS o WHERE o.tvshow.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND o.tvshow.id = :id ");
			params.put("id", tv.getId());
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
