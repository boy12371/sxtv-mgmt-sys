package com.vms.framework.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.action.LogonAction;
import com.vms.common.BaseAction;
import com.vms.common.BaseException;
import com.vms.common.SessionUserInfo;
import com.vms.framework.tabview.TabElementBean;
import com.vms.framework.tabview.TabViewManager;
import com.vms.service.iface.IUserService;

public class BasicLayout extends BaseAction{
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
	private IUserService userService;
	
	private SessionUserInfo userInfo;
	
	public SessionUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(SessionUserInfo userInfo) {
		this.userInfo = userInfo;
		super.setUserInfo(userInfo);
	}

	private List<TabElementBean> tabs;
	
	public String doLogon() throws Exception{
		userInfo = userService.authenticate(userInfo.getUsername(), userInfo.getPassword());
		if(null == userInfo){ 
			this.addActionError("用户名或密码错误");
			return INPUT;
			//throw new BaseException("", "验证失败，用户名或密码错误。")
		}
		this.setUserInfo(userInfo);
		TabViewManager tabManager = TabViewManager.getInstance(this.session);
		tabs = tabManager.getTabs();
		return SUCCESS;
	}

	public void setTabs(List<TabElementBean> tabs) {
		this.tabs = tabs;
	}

	public List<TabElementBean> getTabs() {
		return tabs;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}
}
