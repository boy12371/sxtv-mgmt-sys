package com.vms.action.sysconfig;

import com.vms.common.BaseAction;
import com.vms.db.bean.User;
import com.vms.service.iface.IUserService;

public class UserMgmtAction extends BaseAction {

	
	private IUserService userService;
	
	private User user;
	
	public String toAddUser(){
		return this.SUCCESS;
	}
	
	public String doAddUser() throws Exception{
		userService.createUser(user);
		return this.SUCCESS;
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
	
}
