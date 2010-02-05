package com.vms.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;
import com.vms.db.bean.Role;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.UserRole;
import com.vms.db.bean.UserRolePK;
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
import com.vms.service.iface.IUserRoleService;
import com.vms.service.iface.IUserService;
import com.vms.service.iface.IVediotapeService;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

//		ApplicationContext ctx = new ClassPathXmlApplicationContext(
//				"applicationContext.xml");
//
//		IUserRoleService service = (IUserRoleService) ctx.getBean("userRoleService");
//		IUserService userService = (IUserService) ctx.getBean("userService");
		
		UserRolePK pk =new UserRolePK(new Role(1),new User(1));		
		UserRolePK pk2 =new UserRolePK(new Role(2),new User(1));
		
		UserRole ur =new UserRole(pk);
		UserRole ur2 =new UserRole(pk2);
		
		System.out.println(ur.equals(ur2));
		
		

	}

}
