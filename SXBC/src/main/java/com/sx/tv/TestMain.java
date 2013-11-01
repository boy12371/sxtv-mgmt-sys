package com.sx.tv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.Resource;
import com.sx.tv.entites.Role;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext cxt = new ClassPathXmlApplicationContext("**/*application*.xml");
		//System.out.println(cxt.getBean("userDetailService") != null);

//		EntityManager em = PlayInfo.entityManager();
//		StringBuffer sb = new StringBuffer("SELECT o FROM PlayInfo AS o WHERE o.tvshow.removed = 0 AND o.tvshow.id=179 AND o.tvshow.status.id=16");
//		Query query = em.createQuery(sb.toString(), PlayInfo.class);
//		List<PlayInfo> infos = query.getResultList();
//		for (PlayInfo pi : infos) {
//			System.out.println("========"+pi.getId());
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date d = sdf.parse("2013/08/15");
			Date today = new Date();
			System.out.println(d.after(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
}
