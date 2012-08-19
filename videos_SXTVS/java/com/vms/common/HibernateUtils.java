package com.vms.common;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtils {

	private static final Logger logger = Logger.getLogger(HibernateUtils.class);
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
