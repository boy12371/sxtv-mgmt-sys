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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

		String sql = "select a.* from "
				+ "(select distinct(t.id), t.status,t.name from tvshow t inner join dept_comments d on t.id = d.tvshow) as a"
				+ " inner join tvcontract b on a.id = b.tvshow where a.name like '%高%' order by a.status asc;";
		// String sql =
		// "INSERT INTO tvshow(count,input_date,name,removed,version,company,inputter,progress,projector,status,theme) "
		// +
		// "VALUES (1,'2012-01-01 00:00:00','%NAME%',0,0,1,1,4,1,4,1);";
		// String names =
		// "借问英雄何处,国门英雄,神医喜来乐传奇,当婆婆遇上妈,林师傅在首尔,双枪李向阳之再战松井,爱情闯进门,老爸快跑,漂亮主妇,红色利剑,我是特种兵二利刃出鞘,永不消 逝的电波,骑兵女 侠,外 姓兄弟,一家不说两家 话,让爱回来,浮沉,陆贞传奇,神秘人质,鉄血玫瑰,最爱你,草莽英雄打鬼子,刁蛮俏御医,倾城绝恋,新百娘子传奇,爱情公寓2,风云传奇,我是特种兵二利刃出鞘,爱情是从告白开始的,叶落长安,尖刀队,孝庄秘 史,铁面玫瑰,西游记,打狗棍,结婚秘密,遥远的婚约,鸳鸯配,小鬼子走着瞧,箭在弦上,黎明前的抉择";
		// String[] x = names.split(",");

		// for (String s : x) {
		// //System.out.print("'"+s+"',");
		// System.out.println(sql.replace("%NAME%", s));
		// }
		// System.out.println(cxt.getBean("userDetailService") != null);

		// EntityManager em = TVShow.entityManager();
		try {
		
//			StringBuffer sb = new StringBuffer(
//					"select count(a.id) from "
//							+ "(select distinct(t.id),t.actors,t.comments,t.count,t.directors,t.input_date,"
//							+ "t.is_purchase,t.market_assessment,t.ratings, t.market_share,t.name,t.outline,t.publisher,"
//							+ "t.ranking,t.reject_date,t.removed,t.screenwriters,t.version,t.company,t.inputter,"
//							+ "t.progress,t.projector,t.status,t.theme,t.script_src,t.play_date,t.force_purchase "
//							+ "from tvshow t inner join dept_comments d on t.id = d.tvshow) "
//							+ "as a  inner join tvcontract b on a.id = b.tvshow order by a.status asc");

//			StringBuffer tvsql = new StringBuffer(
//					"select distinct(t.id),t.actors,t.comments,t.count,t.directors,t.input_date,"
//							+ "t.is_purchase,t.market_assessment,t.ratings,t.market_share,t.name,t.outline,t.publisher,"
//							+ "t.ranking,t.reject_date,t.removed,t.screenwriters,t.version,t.company,t.inputter,t.progress,"
//							+ "t.projector,t.status,t.theme,t.script_src,t.play_date,t.force_purchase "
//							+ "from tvshow t inner join tvcontract b on t.id = b.tvshow where t.removed = 0 and b.copyright_end is not null");
			StringBuffer sb = new StringBuffer(
					"select distinct(b.id),b.contract_no,b.copyright_end,b.copyright_start,"
							+ "b.extra_fee,file_agreement_date,b.file_by,b.file_by_agreement,b.file_date,"
							+ "b.input_agreement_date,b.input_date,b.inputter,b.inputter_agreement,owner,"
							+ "b.publish_form,b.recieve_agreement_date,b.recieve_agreement_owner,b.recieve_date,"
							+ "b.recieve_owner,b.show_date,b.total_price,b.version,b.channel,b.tvshow,"
							+ "b.price,b.ex_years,b.extension,b.tape_recive_date,b.tape_store_date,b.comments "
							+ "from tvshow t inner join tvcontract b on t.id = b.tvshow where t.removed = 0 and b.copyright_start is not null and b.copyright_end is not null ");
			StringBuffer count = new StringBuffer(
					"select count(*) from tvshow t inner join tvcontract b on t.id = b.tvshow where t.removed = 0 and b.copyright_start is not null and b.copyright_end is not null ");

//			if (tv.getRecommendChannel() != null || tv.getRecommendLevel() != null) { // dept
//				sb.append("inner join dept_comments d on t.id = d.tvshow");
//				if (tv.getRecommendChannel() != null) {
//					sb.append(" where d.recommend_channel = "
//							+ tv.getRecommendChannel().getId());
//				}
//				if (tv.getRecommendLevel() != null) {
//					sb.append(" where d.recommend_level = "
//							+ tv.getRecommendLevel().getId());
//				}
//			}
			//tvsql.append(")as a ");

//			EntityManager em = TVShow.entityManager();
//			System.out.println(count.toString());
//			Query query = em.createNativeQuery(count.toString());
//			System.out.println(query.getSingleResult());
			
//			EntityManager em = TVContract.entityManager();
//			Query query = em.createNativeQuery(sb.toString(), TVContract.class);
//			int num = query.getResultList().size();
//			System.out.println(num);

			String ss ="select p.id, p.play_date, p.price, p.reserved_from, p.reserved_to, p.round, p.version, p.play_channel,p.tvshow from play_info p inner join tvshow t on p.tvshow=t.id and t.status=14";
			EntityManager em = PlayInfo.entityManager();
			Query query = em.createNativeQuery(ss, PlayInfo.class);
			List<PlayInfo> pi = query.getResultList();
			for (PlayInfo p : pi) {
				System.out.println(p.getTvshow().getName()+"==="+p.getTvshow().getCount()+"==="+p.getPrice());
			}
			//Long x = (Long) query.getSingleResult();
			//java.math.BigInteger num = (BigInteger) query.getSingleResult();
			//System.out.println(num.intValue());
			
			
			// em.getTransaction().begin();
			// Session session = em.unwrap(Session.class);
			// Session session = (Session) em.getDelegate();
			// DataSource ds = (DataSource) cxt.getBean("dataSource");
			// System.out.println(ds != null);
			// Connection conn = ds.getConnection();
			// Statement st = conn.createStatement();
			// ResultSet rs = st.executeQuery("select count(*) from tvshow");
			// int x = 0;
			// while (rs.next()) {
			// x = rs.getInt(1);
			// }
			// System.out.println(x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// StringBuffer sb = new
		// StringBuffer("SELECT o FROM PlayInfo AS o WHERE o.tvshow.removed = 0 AND o.tvshow.id=179 AND o.tvshow.status.id=16");
		// Query query = em.createQuery(sb.toString(), PlayInfo.class);
		// List<PlayInfo> infos = query.getResultList();
		// for (PlayInfo pi : infos) {
		// System.out.println("========"+pi.getId());
		// }
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); try { Date
		 * d = sdf.parse("2013/08/15"); Date today = new Date();
		 * System.out.println(d.after(today)); } catch (ParseException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * EntityManager entityManager = (EntityManager)
		 * cxt.getBean("userDetailService"); List<Role> roles =
		 * entityManager.createQuery("SELECT o FROM Role o", Role.class)
		 * .getResultList();
		 */
		/*
		 * List<Role> roles = Role.findAllRoles(); List<Resource> rs =
		 * Resource.findAllResources(); int count = 0; for (Role role : roles) {
		 * Set<Resource> rr = role.getResources(); for (Resource resource : rr)
		 * { if(!rs.contains(resource)){ System.out.println(resource); } } count
		 * += rr.size(); } System.out.println(count + "=====================" +
		 * rs.size());
		 */
		// List<Role> rlist = new ArrayList<Role>();
		// rlist.add(role);
		// List<User> uList = User.findUsersByRoles(rlist).getResultList();
		//
		// List<Score> sList = Score.findScoresByTvshowidEquals(new
		// Long(2)).getResultList();
		//
		//
		// for (Score score : sList) {
		// System.out.println(score.getRatedBy().getName()+"**"+score.getRatedBy().getId());
		//
		// if (uList.contains(score.getRatedBy())) {
		// uList.remove(score.getRatedBy());
		// }
		// }
		//
		// for (User u : uList) {
		// System.out.println(u.getName() + "==" + u.getId());
		// }

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
