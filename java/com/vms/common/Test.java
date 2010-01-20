package com.vms.common;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.bean.base.BaseStatus;
import com.vms.db.dao.AuditingDAO;
import com.vms.db.dao.BaseRootDAO;
import com.vms.db.dao.StatusDAO;
import com.vms.db.dao.VediotapeDAO;
import com.vms.db.dao.iface.IAuditingDAO;
import com.vms.service.VediotapeService;
import com.vms.service.iface.IVediotapeService;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");		
	
		IVediotapeService service = (IVediotapeService) ctx.getBean("vediotapeService");

	
		try {
			service.findVediotapeByFields(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
