package com.vms.action;

import org.apache.log4j.Logger;

import com.vms.common.BaseAction;
import com.vms.common.SessionUserInfo;

public class LogonAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
	private SessionUserInfo userInfo;
	
	public String toLogon() throws Exception{
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
}
