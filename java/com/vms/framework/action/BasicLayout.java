package com.vms.framework.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.action.LogonAction;
import com.vms.common.BaseAction;
import com.vms.common.SessionUserInfo;
import com.vms.framework.tabview.TabElementBean;
import com.vms.framework.tabview.TabViewManager;

public class BasicLayout extends BaseAction{
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
	private SessionUserInfo userInfo;
	
	private List<TabElementBean> tabs;
	
	public String doLogon() throws Exception{
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
}
