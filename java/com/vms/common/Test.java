package com.vms.common;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;
import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.bean.base.BaseStatus;
import com.vms.db.dao.AuditingDAO;
import com.vms.db.dao.BaseRootDAO;
import com.vms.db.dao.StatusDAO;
import com.vms.db.dao.VediotapeDAO;
import com.vms.db.dao.iface.IAuditingDAO;
import com.vms.service.StatusService;
import com.vms.service.VediotapeService;
import com.vms.service.iface.IEmployeeService;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.IVediotapeService;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	
		//ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");		
	
	//	StatusDAO dao = (StatusDAO) ctx.getBean("statusDAO");
		
		//List list = dao.findObjectByFields(Status.class, null, 0, 100, "id", true);
		String vmname="sdfsdffwefsdf ".replaceAll("#", "%23").replaceAll("&", "%26");
		System.out.println(vmname);
		
		
		
		
	}

}
