package com.vms.action;

import org.apache.log4j.Logger;

import com.vms.common.BaseAction;
import com.vms.common.SessionUserInfo;

public class LogonAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
	private SessionUserInfo userInfo;
	
	private boolean error=false;
	
	
	public String toLogon() throws Exception{
		if(error){
			this.addActionError("用户名密码错误");
		}
		return SUCCESS;
	}
	
	public String doLogon() throws Exception{
		return SUCCESS;
	}

	public void setUserInfo(SessionUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public SessionUserInfo getUserInfo() {
		return userInfo;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
