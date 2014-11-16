package com.sx.tv;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.view.SearchTV;

public class TestMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"**/*application*.xml");

		EntityManagerFactory factory = (EntityManagerFactory) cxt.getBean("entityManagerFactory");
	
		System.out.println(null != factory);
	}

	public List<TVShow> queryDate(SearchTV tv, int firstResult, int size,
			String orderby, String dir) throws UnsupportedEncodingException {
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
}
