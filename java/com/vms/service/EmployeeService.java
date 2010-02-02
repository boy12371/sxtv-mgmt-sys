package com.vms.service;

import java.util.List;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;
import com.vms.db.dao.EmployeeDAO;
import com.vms.db.dao.iface.IEmployeeDAO;
import com.vms.service.iface.IEmployeeService;

public class EmployeeService implements IEmployeeService {

	private IEmployeeDAO employeeDAO;
	private Class clz = com.vms.db.bean.Employee.class;
	@Override
	public void deleteEmployee(int id) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.deleteEmployee(clz,id);
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
		
		return (List<Employee>)employeeDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
		//return employeeDAO.findAllEmployees(clz,startIndex, endIndex);
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
	 * @param voEmp 仅跟新员工信息，不更新其user
	 * @throws Exception
	 */
	

	@Override
	public List<Employee> findAllEmployees() throws Exception {
		// TODO Auto-generated method stub
		return (List<Employee>)employeeDAO.findAll(clz);
	}
	
	
	@Override
	public boolean updateEmployee(EmployeeVO voEmp) throws Exception {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(voEmp);
	}
	
	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}


	

	
}
