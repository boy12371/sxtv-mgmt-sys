package com.vms.action.sysconfig;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.vms.beans.EmployeeVO;
import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.beanutils.BeanConvert;
import com.vms.db.bean.Employee;

import com.vms.service.iface.IEmployeeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmployeeMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EmployeeMgmtAction.class);
	private IEmployeeService employeeService;
	private EmployeeVO vEmployee;
	private Employee employee;
	private JSONDataTable table;
	private String operation;

	public String toEmployees() throws Exception {
		return this.SUCCESS;
	}

	public String getEmployees() throws Exception {
		// table = new JSONTableData();
		table = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<Employee> empList = employeeService.findAllEmployees(table.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir().equals("asc"));
			List<EmployeeVO> vlist = BeanConvert.convertBeans(empList);
			JSONDataTableUtils.setupJSONDataTable(vlist, table, employeeService.getEmployeeTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String toUpdateEmployee() throws Exception {
		try {
			employee = this.employeeService.getEmployeeById(employee.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.INPUT;
		}
		return this.SUCCESS;
	}

	public String doUpdateEmployee() throws Exception {
		vEmployee = BeanConvert.convertBean(employee);

		try {
			if("updateEmp".equals(operation)){
				employeeService.updateEmployee(vEmployee);	
			}else{
				employeeService.enableOrDisableEmployee(vEmployee.getId(), "enableEmp".equals(operation)?true:false);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.addActionError("更新失败");
			return this.INPUT;
		}

		return this.SUCCESS;
	}

	public String toAddEmployee() {
		return this.SUCCESS;
	}

	public String doAddEmployee() throws Exception {
		try {
			employeeService.createEmployee(employee);
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.addActionError("添加失败");
			return this.INPUT;
		}
		this.addActionMessage("添加成功");
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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
