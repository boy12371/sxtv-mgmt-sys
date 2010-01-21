package com.vms.common;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vms.db.bean.Employee;
import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.bean.base.BaseStatus;
import com.vms.db.dao.AuditingDAO;
import com.vms.db.dao.BaseRootDAO;
import com.vms.db.dao.StatusDAO;
import com.vms.db.dao.VediotapeDAO;
import com.vms.db.dao.iface.IAuditingDAO;
import com.vms.service.VediotapeService;
import com.vms.service.iface.IEmployeeService;
import com.vms.service.iface.IVediotapeService;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	
		ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");		
	
		IEmployeeService service = (IEmployeeService) ctx.getBean("employeeService");

	
		try {
		//	service.findVediotapeByFields(null);
			Employee employee=new Employee();
			employee.setBirthday(new Date());
			employee.setContractDate(new Date());
			employee.setComments("这是一个员工");
			employee.setGender(1);
			employee.setName("周强");
			employee.setTel("13991366931");
			service.createEmployee(employee);
			List<Employee> list = service.findAllEmployees(0, -1);
			
			for(Employee e : list){
				System.out.println(e.getName()+"/"+ e.getId());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
