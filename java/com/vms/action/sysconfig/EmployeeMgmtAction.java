package com.vms.action.sysconfig;

import java.util.List;

import com.vms.common.BaseAction;
import com.vms.db.bean.Employee;
import com.vms.service.iface.IEmployeeService;

public class EmployeeMgmtAction extends BaseAction{
	
	private IEmployeeService employeeService;
	
	private Employee employee;
	private List<Employee> empList;
	
	
	public String toEmployees() throws Exception{
		empList = employeeService.findAllEmployees(0, -1);
		return this.SUCCESS;
		
	}

	
	public String toAddEmployee(){
		return this.SUCCESS;
	}
	public String doAddEmployee() throws Exception{
		employeeService.createEmployee(employee);
		return this.SUCCESS;
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
