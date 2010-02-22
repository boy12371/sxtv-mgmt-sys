package com.vms.action.sysconfig;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.SessionUserInfo;
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
	private List<Integer> roleIDs;
	private JSONDataTable table;
	private String operation;
	private String password;
	private String newPass;
	private String confirmPass;

	public String toAddUser() throws Exception {
		return this.SUCCESS;
	}

	public String doAddUser() throws Exception {
		try {
			userService.createUser(user, roleIDs);
		} catch (Exception e) {
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
							.getSort(), table.getDir().equals(
							JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(users, table, userService
					.getUserTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String toUpdateUser() throws Exception {
		try {
			user = userService.getUserById(user.getId());
			Set<Role> r = user.getRoles();
			Iterator<Role> it = r.iterator();
			while (it.hasNext()) {
				Role role = it.next();
				System.out.println(role.getId() + "===" + role.getName());

			}

		} catch (Exception e) {
			logger.error(e);
			return INPUT;
		}

		return SUCCESS;
	}

	public String doUpdateUser() throws Exception {
		boolean succeed = false;
		try {
			succeed = userService.updateUser(operation, user, roleIDs);
		} catch (Exception e) {
			logger.error(e);
			this.addActionError("操作失败.");
			return INPUT;
		}
		if (!succeed) {
			this.addActionError("操作失败.");
			return INPUT;
		}
		this.addActionMessage("操作成功");
		return SUCCESS;
	}

	public String doChangePassword() throws Exception {
		this.getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		SessionUserInfo user = this.getUserInfo();
		if (user.getPassword().equals(this.getPassword())) {
			if(userService.updateUserPassword(user.getUserId(), this.getNewPass())){
				out.print("SUCCESS");
			}else{
				out.print("操作失败");
			}			
		} else {
			out.print("密码输入错误");
		}
		out.close();
		return NONE;
	}

	public List<Employee> getEmpList() throws Exception {

		return this.employeeService.findAllUnassignedEmployees();
	}

	public List<Role> getRoleList() throws Exception {
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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(List roleIDs) {
		this.roleIDs = roleIDs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

}
