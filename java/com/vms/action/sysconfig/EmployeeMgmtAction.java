package com.vms.action.sysconfig;

import java.util.ArrayList;
import java.util.List;

import com.vms.beans.EmployeeVO;
import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.beanutils.BeanConvert;
import com.vms.db.bean.Employee;
import com.vms.db.bean.MyEmp;
import com.vms.service.iface.IEmployeeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmployeeMgmtAction extends BaseAction {

	private IEmployeeService employeeService;

	private EmployeeVO vEmployee;
	private Employee employee;
	private JSONDataTable table;

	public String toEmployees() throws Exception {
		return this.SUCCESS;
	}

	public String getEmployees() throws Exception {
		// table = new JSONTableData();
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		List<Employee> empList = employeeService.findAllEmployees(table.getStartIndex(), table.getStartIndex()
				+ table.getRowsPerPage(), table.getSort(), table.getDir().equals("asc"));
		List<EmployeeVO> vlist = BeanConvert.convertBeans(empList);
		JSONDataTableUtils.setupJSONDataTable(vlist, table, employeeService.getEmployeeTotalCount());

		return this.SUCCESS;

	}

	
	public String toUpdateEmployee()throws Exception{
		employee = this.employeeService.getEmployeeById(employee.getId());
		return this.SUCCESS;
	}
	public String toAddEmployee() {
		return this.SUCCESS;
	}

	public String doAddEmployee() throws Exception {
		//employeeService.createEmployee(employee);
		return this.SUCCESS;
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}

	public EmployeeVO getVEmployee() {
		return vEmployee;
	}

	public void setVEmployee(EmployeeVO employee) {
		vEmployee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}
