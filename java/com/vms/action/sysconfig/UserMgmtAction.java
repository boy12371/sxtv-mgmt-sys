package com.vms.action.sysconfig;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.beanutils.BeanConvert;

import com.vms.db.bean.Employee;
import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.service.iface.IEmployeeService;
import com.vms.service.iface.IRoleService;
import com.vms.service.iface.IUserService;

public class UserMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EmployeeMgmtAction.class);
	private IUserService userService;
	private IEmployeeService employeeService;
	private IRoleService roleService;

	private User user;
	private JSONDataTable table;

	public String toAddUser()throws Exception {
		return this.SUCCESS;
	}

	public String doAddUser() throws Exception {
		try{
			userService.createUser(user);	
		}catch(Exception e){
			logger.error(e.getMessage());
			this.addActionError("用户创建失败");
			return INPUT;
		}
		this.addActionMessage("用户创建成功");
		return this.SUCCESS;
	}

	public String getUsers() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());

		try {
			List<User> users = userService.findAllUser(table.getStartIndex(),
					table.getStartIndex() + table.getRowsPerPage(), table
							.getSort(), table.getDir().equals("asc"));
			JSONDataTableUtils.setupJSONDataTable(users, table, userService
					.getUserTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String toUpdateUser()throws Exception{
		return SUCCESS;
	}
	
	public String resetUserPassword()throws Exception{
		return SUCCESS;
	}
	
	public String updateUserRole() throws Exception{
		return SUCCESS;	
	}
	
	public String disableUser()throws Exception{
		return SUCCESS;
	}
	
	public String enableUser()throws Exception{
		return SUCCESS;
	}
	
	
	public List<Employee> getEmpList()throws Exception{
		
		return this.employeeService.findAllEmployees();
		
	}
	
	public List<Role> getRoleList()throws Exception{
		return roleService.findAllRoles();
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}
}
