package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;
import com.vms.db.bean.User;
import com.vms.db.dao.EmployeeDAO;
import com.vms.db.dao.iface.IEmployeeDAO;
import com.vms.service.iface.IEmployeeService;

public class EmployeeService implements IEmployeeService {

	private IEmployeeDAO employeeDAO;
	private Class clz = com.vms.db.bean.Employee.class;

	@Override
	public void deleteEmployee(int id) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.deleteEmployee(clz, id);
	}

	@Override
	public void deleteEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.deleteObject(employee);
	}

	@Override
	public List<Employee> findAllEmployees(int startIndex, int endIndex, String propertyName, boolean ascending)
			throws Exception {
		// TODO Auto-generated method stub

		return (List<Employee>) employeeDAO
				.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
		// return employeeDAO.findAllEmployees(clz,startIndex, endIndex);
	}

	@Override
	public void createEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.saveObject(employee);
	}

	@Override
	public Employee getEmployeeById(int id) throws Exception {
		// TODO Auto-generated method stub
		return (Employee) employeeDAO.getObject(clz, id);
	}

	@Override
	public int getEmployeeTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return employeeDAO.getObjectTotalCount(clz, Employee.PROP_ID);
	}

	/***
	 * 更新员工信息
	 * 
	 * @param voEmp
	 *            仅跟新员工信息，不更新其user
	 * @throws Exception
	 */

	@Override
	public List<Employee> findAllEmployees() throws Exception {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeDAO.findAll(clz);
	}

	@Override
	public boolean updateEmployee(EmployeeVO voEmp) throws Exception {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(voEmp);
	}

	@Override
	public List<Employee> findAllUnassignedEmployees() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> propertiesValues = new HashMap<String, Object>();
		propertiesValues.put(Employee.PROP_STATUS, 1);
		List<Employee> empList = (List<Employee>) employeeDAO.findObjectByFields(clz, propertiesValues, -1, -1,
				Employee.PROP_ID, true);
		List<Employee> unassigned = new ArrayList<Employee>();
		for (Employee object : empList) {
			if (object.getUsers().isEmpty()) {
				unassigned.add(object);
			}
		}
		return unassigned;
	}

	
	
	@Override
	public boolean enableOrDisableEmployee(int id, boolean isEnable)
			throws Exception {
		Employee emp = (Employee) employeeDAO.getObject(clz, id);
		emp.setStatus(isEnable?1:0);
		Set<User> empUser  =  emp.getUsers();
		if(!empUser.isEmpty()){
			Iterator<User> it  = empUser.iterator();
			while (it.hasNext()) {
				User u = it.next();
				u.setStatus(isEnable?1:0);
			}
		}
		employeeDAO.updateObject(emp);
		return true;
	}
	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}



	



}
