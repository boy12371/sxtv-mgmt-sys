package com.vms.service;

import java.util.List;

import com.vms.db.bean.Employee;
import com.vms.db.dao.iface.IEmployeeDAO;
import com.vms.service.iface.IEmployeeService;

public class EmployeeService implements IEmployeeService {

	private IEmployeeDAO employeeDAO;

	@Override
	public void deleteEmployee(int id) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.deleteEmployee(id);
	}

	@Override
	public void deleteEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.delete(employee);
	}

	@Override
	public List<Employee> findAllEmployees(int startIndex, int endIndex)
			throws Exception {
		// TODO Auto-generated method stub
		return employeeDAO.findAllEmployees(startIndex, endIndex);
	}

	@Override
	public void createEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		employeeDAO.save(employee);
	}

	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
