package com.vms.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.vms.beans.EmployeeVO;
import com.vms.beans.JSONDataTable;
import com.vms.db.bean.Employee;
import com.vms.db.bean.Role;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.UserRole;
import com.vms.db.bean.UserRolePK;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.bean.base.BaseStatus;
import com.vms.db.dao.AuditingDAO;
import com.vms.db.dao.BaseRootDAO;
import com.vms.db.dao.StatusDAO;
import com.vms.db.dao.VediotapeDAO;
import com.vms.db.dao.iface.IAuditingDAO;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.service.StatusService;
import com.vms.service.VediotapeService;
import com.vms.service.iface.IEmployeeService;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.IUserRoleService;
import com.vms.service.iface.IUserService;
import com.vms.service.iface.IVedioscoreService;
import com.vms.service.iface.IVediotapeService;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//
//		IUserService service = (IUserService) ctx.getBean("userService");
//		User user  =  service.getUserById(1);
//		
//		GrantedAuthority[] gas = user.getAuthorities();
//		System.out.println(gas.length);
		 
		// ctx.getBean("userRoleService");
		//IVediotapeService service = (IVediotapeService) ctx.getBean("vediotapeService");

		//int count = service.getTotalCountForVideosByStatus(2);
		//System.out.println(count);
		
		
		//IPlayorderDAO service = (IPlayorderDAO) ctx.getBean("playorderDAO");
		//Calendar cal = Calendar.getInstance();
		//cal.set(Calendar.YEAR, 2010);
		//cal.set(2010, 02, 01);
		
		//System.out.println(cal.getTime());
		//List list = service.findPlayorderByMonth(cal.getTime());
		//System.out.println(list.size());
		
//		for (Vediotape vediotape : list) {
//			System.out.println(vediotape.getId()+" / "+vediotape.getVedioName());
//		}
		
		//List<String> names = service.findVideoNamesForAutoComplete();
//		
//		for (int i = 0; i < names.size(); i++) {
//			System.out.println(names.get(i));
//		}
//		JSONArray array = JSONArray.fromObject(names);
//		System.out.println(array);
		//List list = service.getUserExaminedVedioes("cat", 0, 10, "score", true);
		//System.out.println(flag);
		
		// cal.get
		// dao.findVedioesInPeriod(dateStart, dateEnd, propertiesValues,
		// startIndex, endIndex, propertyName, JSONDataTableUtils.SORT_DIRECTIONending)
		// UserRolePK pk =new UserRolePK(new Role(1),new User(1));
		// UserRolePK pk2 =new UserRolePK(new Role(2),new User(1));
		//		
		// UserRole ur =new UserRole(pk);
		// UserRole ur2 =new UserRole(pk2);
		//		
		// System.out.println(ur.equals(ur2));

		// String ss =
		// "[{\"birthday\":\"1983-02-19T00:00:00\",\"comments\":\"这是一个员工\",\"contractDate\":\"2006-01-16T00:00:00\",\"gender\":0,\"id\":1,\"name\":\"王文婷\",\"tel\":\"13991366931\"},"
		// +
		// "{\"birthday\":\"2010-01-21T00:00:00\",\"comments\":\"王萌王萌王萌\",\"contractDate\":\"1970-01-01T00:00:00\",\"gender\":0,\"id\":2,\"name\":\"王萌\",\"tel\":\"13991366930\"}]"
		// ;
		// JsonConfig config =new JsonConfig();
		// config.setJsonPropertyFilter(new PropertyFilter(){
		// @Override
		// public boolean apply(Object arg0, String arg1, Object arg2) {
		// // TODO Auto-generated method stub
		//				
		// return false;
		// }});

		// JSONArray jsArray = JSONArray.fromObject(ss);
		// System.out.println(jsArray.size());
		// System.out.println(jsArray.isArray());
		// System.out.println(jsArray.getJSONObject(0));

		// JSONObject o = jsArray.getJSONObject(0);
		// System.out.println(o.get("comments"));
		
		
		//System.out.println(Integer.toHexString(98).toUpperCase());
		//PasswordEncoder md=new MessageDigestPasswordEncoder("MD5");
		
		//System.out.println(md.encodePassword("2", null));
		
		

	}
	
}
