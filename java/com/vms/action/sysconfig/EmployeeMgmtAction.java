package com.vms.action.sysconfig;

import java.util.ArrayList;
import java.util.List;

import com.vms.common.BaseAction;
import com.vms.db.bean.Employee;
import com.vms.db.bean.MyEmp;
import com.vms.service.iface.IEmployeeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmployeeMgmtAction extends BaseAction {

	private IEmployeeService employeeService;

	private Employee employee;
	private List<Employee> empList;

	private JSONTableData data;

	public String toEmployees() throws Exception {
		return this.SUCCESS;

	}

	public String getEmployees() throws Exception {
		data = new JSONTableData();
		data.setTotalRecords(15);
		empList = employeeService.findAllEmployees(0, -1);
		List<MyEmp> myEmp = new ArrayList();
		for (int i = 0; i < empList.size(); i++) {
			MyEmp e = new MyEmp();
			e.setId(empList.get(i).getId());
			e.setName(empList.get(i).getName());
			myEmp.add(e);
		}
		data.setRecords(myEmp);

		return this.SUCCESS;

	}

	public String toAddEmployee() {
		return this.SUCCESS;
	}

	public String doAddEmployee() throws Exception {
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

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public JSONTableData getData() {
		return data;
	}

	public void setData(JSONTableData data) {
		this.data = data;
	}

}
