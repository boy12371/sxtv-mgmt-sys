package com.sx.tv.finder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.TVShowData;
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
		if (tv.getCopyrightFrom() != null || tv.getCopyrightTo() != null
				|| tv.getCtcInputDateEnd() != null
				|| tv.getCtcInputDateStart() != null) { // contract
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

		if (null != tv.getStatus() && !tv.getStatus().isEmpty()) {
			sb.append(" AND a.status IN :status");
			List<Integer> _ids = new ArrayList<Integer>(tv.getStatus().size());
			for (Status s : tv.getStatus()) {
				if(null != s){
					_ids.add(s.getId());	
				}
			}
			params.put("status", _ids);
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

		sb.append(" order by a." + orderby + " " + dir);
		EntityManager em = TVShow.entityManager();
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

	public static List<TVContract> findTVshows4MarketLevel2(SearchTV tv,
			int firstResult, int size, String orderby, String dir)
			throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<TVContract>();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(
				"select distinct(c.id),c.contract_no,c.copyright_end,c.copyright_start,"
						+ "c.extra_fee,file_agreement_date,c.file_by,c.file_by_agreement,c.file_date,"
						+ "c.input_agreement_date,c.input_date,c.inputter,c.inputter_agreement,owner,"
						+ "c.publish_form,c.recieve_agreement_date,c.recieve_agreement_owner,c.recieve_date,"
						+ "c.recieve_owner,c.show_date,c.total_price,c.version,c.channel,c.tvshow,"
						+ "c.price,c.ex_years,c.extension,c.tape_recive_date,c.tape_store_date,c.comments "
						+ "from tvshow t inner join tvcontract c on t.id = c.tvshow ");// where
																						// t.removed
																						// =
																						// 0
																						// and
																						// c.copyright_start
																						// is
																						// not
																						// null
																						// and
																						// c.copyright_end
																						// is
																						// not
																						// null
																						// ");

		int round = 1;
		boolean hasWhere = false;
		if (null != tv.getStatus() && !tv.getStatus().isEmpty()) {
			int sid = tv.getStatus().get(0).getId();
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

			if (round != 1) {
				sb.append(" inner join play_info p on t.id = p.tvshow ");
			}
			if (hasWhere) {
				sb.append(" AND t.status = :status ");
			} else {
				sb.append(" WHERE t.status = :status ");
				hasWhere = true;
			}

			params.put("status", tv.getStatus());
		}

		if (round == 1) {
			if (null != tv.getPlayDateStart()) {
				if (hasWhere) {
					sb.append(" AND t.play_date >= :playDateS ");
				} else {
					sb.append(" WHERE t.play_date >= :playDateS ");
					hasWhere = true;
				}
				params.put("playDateS", tv.getPlayDateStart());
			}
			if (null != tv.getPlayDateEnd()) {
				if (hasWhere) {
					sb.append(" AND t.play_date <= :playDateE ");
				} else {
					sb.append(" WHERE t.play_date <= :playDateE ");
					hasWhere = true;
				}
				params.put("playDateE", tv.getPlayDateEnd());
			}

			if (null != tv.getPlayChannel()) {
				if (hasWhere) {
					sb.append(" AND c.channel = :channel");
				} else {
					sb.append(" WHERE c.channel = :channel");
					hasWhere = true;
				}
				params.put("channel", tv.getPlayChannel());
			}

			if (null != tv.getCopyrightFrom()) {
				if (hasWhere) {
					sb.append(" AND c.copyright_start >= :copyright_start");
				} else {
					sb.append(" WHERE c.copyright_start >= :copyright_start");
					hasWhere = true;
				}
				params.put("copyright_start", tv.getCopyrightFrom());
			}
			if (null != tv.getCopyrightTo()) {
				if (hasWhere) {
					sb.append(" AND c.copyright_end <= :copyright_end");
				} else {
					sb.append(" WHERE c.copyright_end <= :copyright_end");
					hasWhere = true;
				}
				params.put("copyright_end", tv.getCopyrightTo());
			}
		} else {
			if (null != tv.getPlayDateStart()) {
				if (hasWhere) {
					sb.append(" AND p.play_date >= :playDateS ");
				} else {
					sb.append(" WHERE p.play_date >= :playDateS ");
					hasWhere = true;
				}
				params.put("playDateS", tv.getPlayDateStart());
			}
			if (null != tv.getPlayDateEnd()) {
				if (hasWhere) {
					sb.append(" AND p.play_date <= :playDateE ");
				} else {
					sb.append(" WHERE p.play_date <= :playDateE ");
					hasWhere = true;
				}
				params.put("playDateE", tv.getPlayDateEnd());
			}
			//sb.append(" AND p.round = " + round);

			if (null != tv.getPlayChannel()) {
				if (hasWhere) {
					sb.append(" AND p.play_channel = :play_channel");
				} else {
					sb.append(" WHERE p.play_channel = :play_channel");
					hasWhere = true;
				}
				params.put("play_channel", tv.getPlayChannel());
			}

			if (null != tv.getCopyrightFrom()) {
				if (hasWhere) {
					sb.append(" AND p.reserved_from >= :reserved_from");
				} else {
					sb.append(" WHERE p.reserved_from >= :reserved_from");
					hasWhere = true;
				}
				params.put("reserved_from", tv.getCopyrightFrom());
			}
			if (null != tv.getCopyrightTo()) {

				if (hasWhere) {
					sb.append(" AND p.reserved_to <= :reserved_to");
				} else {
					sb.append(" WHERE p.reserved_to <= :reserved_to");
					hasWhere = true;
				}
				params.put("reserved_to", tv.getCopyrightTo());
			}
		}

		if (null != tv.getId() && !"".equals(tv.getId())) {
			if (hasWhere) {
				sb.append(" AND t.id = :id ");
			} else {
				sb.append(" WHERE t.id = :id ");
				hasWhere = true;
			}
			params.put("id", tv.getId());
		}
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			if (hasWhere) {
				sb.append(" AND t.name LIKE :name ");
			} else {
				sb.append(" WHERE t.name LIKE :name ");
				hasWhere = true;
			}
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			if (hasWhere) {
				sb.append(" AND t.company = :company ");
			} else {
				sb.append(" WHERE t.company = :company ");
				hasWhere = true;
			}
			params.put("company", tv.getCompany());
		}
		if (null != tv.getProgress()) {

			if (hasWhere) {
				sb.append(" AND t.progress = :progress ");
			} else {
				sb.append(" WHERE t.progress = :progress ");
				hasWhere = true;
			}
			params.put("progress", tv.getProgress());
		}
		if (null != tv.getTheme()) {

			if (hasWhere) {
				sb.append(" AND t.theme = :theme ");
			} else {
				sb.append(" WHERE t.theme = :theme ");
				hasWhere = true;
			}
			params.put("theme", tv.getTheme());
		}
		if (null != tv.getProjector()) {
			if (hasWhere) {
				sb.append(" AND t.projector = :projector ");
			} else {
				sb.append(" WHERE t.projector = :projector ");
				hasWhere = true;
			}
			params.put("projector", tv.getProjector());
		}

		if (hasWhere) {
			sb.append(" AND t.removed = 0 and c.copyright_start is not null and c.copyright_end is not null ");
		} else {
			sb.append(" WHERE t.removed = 0 and c.copyright_start is not null and c.copyright_end is not null ");
		}

		if (round != 1) {
			if ("playChannel".equalsIgnoreCase(orderby)) {
				sb.append("ORDER BY p.play_channel " + dir);
			} else if ("price".equalsIgnoreCase(orderby)) {
				sb.append("ORDER BY p.price " + dir);
			} else {
				sb.append("ORDER BY t." + orderby + " " + dir);
			}
		} else {
			if ("playChannel".equalsIgnoreCase(orderby)) {
				sb.append("ORDER BY c.channel " + dir);
			} else if ("price".equalsIgnoreCase(orderby)) {
				sb.append("ORDER BY c.price " + dir);
			} else {
				sb.append("ORDER BY t." + orderby + " " + dir);
			}
		}

		EntityManager em = TVContract.entityManager();
		Query query = em.createNativeQuery(sb.toString(), TVContract.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		List<TVContract> cts = query.setFirstResult(firstResult)
				.setMaxResults(size).getResultList();
		return cts;
	}

	public static long countTVshows4MarketLevel2(SearchTV tv)
			throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(
				"select count(*) from tvshow t inner join tvcontract c on t.id = c.tvshow ");
		int round = 1;
		boolean hasWhere = false;
		if (null != tv.getStatus() && !tv.getStatus().isEmpty()) {
			int sid = tv.getStatus().get(0).getId();
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

			if (round != 1) {
				sb.append(" inner join play_info p on t.id = p.tvshow ");
			}
			if (hasWhere) {
				sb.append(" AND t.status = :status ");
			} else {
				sb.append("WHERE t.status = :status ");
				hasWhere = true;
			}
			params.put("status", tv.getStatus().get(0).getId());
		}

		if (round == 1) {
			if (null != tv.getPlayDateStart()) {
				if (hasWhere) {
					sb.append(" AND t.play_date >= :playDateS ");
				} else {
					sb.append(" WHERE t.play_date >= :playDateS ");
					hasWhere = true;
				}
				params.put("playDateS", tv.getPlayDateStart());
			}
			if (null != tv.getPlayDateEnd()) {
				if (hasWhere) {
					sb.append(" AND t.play_date <= :playDateE ");
				} else {
					sb.append(" WHERE t.play_date <= :playDateE ");
					hasWhere = true;
				}
				params.put("playDateE", tv.getPlayDateEnd());
			}

			if (null != tv.getPlayChannel()) {
				if (hasWhere) {
					sb.append(" AND c.channel = :channel");
				} else {
					sb.append(" WHERE c.channel = :channel");
					hasWhere = true;
				}
				params.put("channel", tv.getPlayChannel());
			}

			if (null != tv.getCopyrightFrom()) {
				if (hasWhere) {
					sb.append(" AND c.copyright_start >= :copyright_start");
				} else {
					sb.append(" WHERE c.copyright_start >= :copyright_start");
					hasWhere = true;
				}
				params.put("copyright_start", tv.getCopyrightFrom());
			}
			if (null != tv.getCopyrightTo()) {
				if (hasWhere) {
					sb.append(" AND c.copyright_end <= :copyright_end");
				} else {
					sb.append(" WHERE c.copyright_end <= :copyright_end");
					hasWhere = true;
				}
				params.put("copyright_end", tv.getCopyrightTo());
			}

		} else {
			if (null != tv.getPlayDateStart()) {
				if (hasWhere) {
					sb.append(" AND p.play_date >= :playDateS ");
				} else {
					sb.append(" WHERE p.play_date >= :playDateS ");
					hasWhere = true;
				}
				params.put("playDateS", tv.getPlayDateStart());
			}
			if (null != tv.getPlayDateEnd()) {
				if (hasWhere) {
					sb.append(" AND p.play_date <= :playDateE ");
				} else {
					sb.append(" WHERE p.play_date <= :playDateE ");
					hasWhere = true;
				}
				params.put("playDateE", tv.getPlayDateEnd());
			}
			//sb.append(" AND p.round = " + round);

			if (null != tv.getPlayChannel()) {
				if (hasWhere) {
					sb.append(" AND p.play_channel = :play_channel");
				} else {
					sb.append(" WHERE p.play_channel = :play_channel");
					hasWhere = true;
				}
				params.put("play_channel", tv.getPlayChannel());
			}

			if (null != tv.getCopyrightFrom()) {
				if (hasWhere) {
					sb.append(" AND p.reserved_from >= :reserved_from");
				} else {
					sb.append(" WHERE p.reserved_from >= :reserved_from");
					hasWhere = true;
				}
				params.put("reserved_from", tv.getCopyrightFrom());
			}
			if (null != tv.getCopyrightTo()) {

				if (hasWhere) {
					sb.append(" AND p.reserved_to <= :reserved_to");
				} else {
					sb.append(" WHERE p.reserved_to <= :reserved_to");
					hasWhere = true;
				}
				params.put("reserved_to", tv.getCopyrightTo());
			}
		}

		if (null != tv.getId() && !"".equals(tv.getId())) {
			if (hasWhere) {
				sb.append(" AND t.id = :id ");
			} else {
				sb.append(" WHERE t.id = :id ");
				hasWhere = true;
			}
			params.put("id", tv.getId());
		}
		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			if (hasWhere) {
				sb.append(" AND t.name LIKE :name ");
			} else {
				sb.append(" WHERE t.name LIKE :name ");
				hasWhere = true;
			}
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			if (hasWhere) {
				sb.append(" AND t.company = :company ");
			} else {
				sb.append(" WHERE t.company = :company ");
				hasWhere = true;
			}
			params.put("company", tv.getCompany());
		}
		if (null != tv.getProgress()) {

			if (hasWhere) {
				sb.append(" AND t.progress = :progress ");
			} else {
				sb.append(" WHERE t.progress = :progress ");
				hasWhere = true;
			}
			params.put("progress", tv.getProgress());
		}
		if (null != tv.getTheme()) {

			if (hasWhere) {
				sb.append(" AND t.theme = :theme ");
			} else {
				sb.append(" WHERE t.theme = :theme ");
				hasWhere = true;
			}
			params.put("theme", tv.getTheme());
		}
		if (null != tv.getProjector()) {
			if (hasWhere) {
				sb.append(" AND t.projector = :projector ");
			} else {
				sb.append(" WHERE t.projector = :projector ");
				hasWhere = true;
			}
			params.put("projector", tv.getProjector());
		}

		if (hasWhere) {
			sb.append(" AND t.removed = 0 and c.copyright_start is not null and c.copyright_end is not null ");
		} else {
			sb.append(" WHERE t.removed = 0 and c.copyright_start is not null and c.copyright_end is not null ");
		}

		EntityManager em = TVContract.entityManager();
		Query query = em.createNativeQuery(sb.toString());
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		java.math.BigInteger num = (BigInteger) query.getSingleResult();
		return num.longValue();
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
		if (tv.getCopyrightFrom() != null || tv.getCopyrightTo() != null
				|| tv.getCtcInputDateEnd() != null
				|| tv.getCtcInputDateStart() != null) { // contract
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

		if (null != tv.getStatus() && !tv.getStatus().isEmpty()) {
			sb.append(" AND a.status IN :status");
			List<Integer> _ids = new ArrayList<Integer>(tv.getStatus().size());
			for (Status s : tv.getStatus()) {
				if(null != s){
					_ids.add(s.getId());	
				}
				
			}
			params.put("status", _ids);
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

		EntityManager em = TVShow.entityManager();
		Query query = em.createNativeQuery(sb.toString());
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}

		java.math.BigInteger num = (BigInteger) query.getSingleResult();
		return num.longValue();
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

	public static List<PlayInfo> findTVShowsTransactions4MarketLevel2(
			SearchTV tv) throws UnsupportedEncodingException {
		if (null == tv) {
			return new ArrayList<PlayInfo>();
		}
		EntityManager em = PlayInfo.entityManager();
		StringBuffer sb = new StringBuffer(
				"select p.id, p.play_date, p.price, p.reserved_from, p.reserved_to, p.round, p.version, p.play_channel,"
						+ "p.tvshow from play_info p inner join tvshow t on p.tvshow = t.id inner join tvcontract c on t.id = c.tvshow and t.removed = 0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != tv.getId() && !"".equals(tv.getId())) {
			sb.append("AND t.id = :id ");
			params.put("id", tv.getId());
		}

		if (null != tv.getName() && !"".equals(tv.getName())) {
			String tname = new String(tv.getName().getBytes("ISO-8859-1"),
					"UTF-8");
			String _name = convertLikeString(tname.replace('*', '%'));
			sb.append("AND t.name LIKE :name ");
			params.put("name", _name);
		}
		if (null != tv.getCompany()) {
			sb.append("AND t.company = :company ");
			params.put("company", tv.getCompany());
		}

		if (null != tv.getProgress()) {
			sb.append("AND t.progress = :progress ");
			params.put("progress", tv.getProgress());
		}

		if (null != tv.getTheme()) {
			sb.append("AND t.theme = :theme ");
			params.put("theme", tv.getTheme());
		}

		int round = 1;

		if (null != tv.getStatus() && !tv.getStatus().isEmpty()) {
			sb.append("AND t.status = :status ");
			params.put("status", tv.getStatus());
			int sid = tv.getStatus().get(0).getId();
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
			sb.append(" AND t.inputter = :inputter ");
			params.put("inputter", tv.getProjector());
		}

		if (round == 1) {
			if (null != tv.getPlayDateStart()) {
				sb.append("AND t.playDate >= :playDate ");
				params.put("playDate", tv.getPlayDateStart());
			}
			if (null != tv.getPlayDateEnd()) {
				sb.append("AND t.playDate <= :playDate ");
				params.put("playDate", tv.getPlayDateEnd());
			}

			if (null != tv.getPlayChannel()) {
				sb.append(" AND c.channel = :channel");
				params.put("channel", tv.getPlayChannel());
			}

			if (null != tv.getCopyrightFrom()) {
				sb.append(" AND c.copyright_start >= :copyright_start");
				params.put("copyright_start", tv.getCopyrightFrom());
			}
			if (null != tv.getCopyrightTo()) {
				sb.append(" AND c.copyright_start <= :copyright_end");
				params.put("copyright_end", tv.getCopyrightTo());
			}

		} else {
			if (null != tv.getPlayDateStart()) {
				sb.append("AND p.playDate >= :playDate ");
				params.put("playDate", tv.getPlayDateStart());
			}
			if (null != tv.getPlayDateEnd()) {
				sb.append("AND p.playDate <= :playDate ");
				params.put("playDate", tv.getPlayDateEnd());
			}
			sb.append(" AND p.round = " + round);

			if (null != tv.getPlayChannel()) {
				sb.append(" AND p.play_channel = :play_channel");
				params.put("play_channel", tv.getPlayChannel());
			}

			if (null != tv.getCopyrightFrom()) {
				sb.append(" AND p.reserved_from >= :reserved_from");
				params.put("reserved_from", tv.getCopyrightFrom());
			}
			if (null != tv.getCopyrightTo()) {
				sb.append(" AND p.reserved_to <= :reserved_to");
				params.put("reserved_to", tv.getCopyrightTo());
			}
		}

		Query query = em.createNativeQuery(sb.toString(), PlayInfo.class);
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		List<PlayInfo> infos = query.getResultList();
		return infos;
	}

	@SuppressWarnings("unchecked")
	public static PlayInfo getPlayInfo(long tv, int round) {
		String oper = "=";
		if(round >= 4){
			oper = ">=";
		}
		EntityManager em = PlayInfo.entityManager();
		StringBuffer sb = new StringBuffer(
				"select p.id, p.play_date, p.price, p.reserved_from, p.reserved_to, p.round, p.version, p.play_channel,"
						+ "p.tvshow from play_info p where p.tvshow ="
						+ tv
						+ " and p.round " + oper + round+" order by p.round desc");
		
		Query query = em.createNativeQuery(sb.toString(), PlayInfo.class);
		List<PlayInfo> pi = query.getResultList();
		if(null != pi && !pi.isEmpty()){
			return pi.get(0);
		}
		return null;
	}
}
