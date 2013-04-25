package com.sx.tv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sx.tv.entites.Resource;
import com.sx.tv.entites.Role;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext cxt = new ClassPathXmlApplicationContext("**/*application*.xml");
		System.out.println(cxt.getBean("userDetailService") != null + "=================");
		/*
		 * EntityManager entityManager = (EntityManager)
		 * cxt.getBean("userDetailService"); List<Role> roles =
		 * entityManager.createQuery("SELECT o FROM Role o", Role.class)
		 * .getResultList();
		 */
		List<Role> roles = Role.findAllRoles();
		List<Resource> rs = Resource.findAllResources();
		int count = 0;
		for (Role role : roles) {
			Set<Resource> rr = role.getResources();
			for (Resource resource : rr) {
				if(!rs.contains(resource)){
					System.out.println(resource);
				}
			}
			count += rr.size();
		}
		System.out.println(count + "=====================" + rs.size());
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
