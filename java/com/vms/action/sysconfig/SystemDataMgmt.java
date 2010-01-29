package com.vms.action.sysconfig;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Status;
import com.vms.db.bean.Topic;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.ITopicService;

public class SystemDataMgmt extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SystemDataMgmt.class);
	private IStatusService statusService;
	private ITopicService topicService;

	private JSONDataTable table;
	private List<Status> staList;
	private List<Topic> topList;

	public String toSystemData(){
		return this.SUCCESS;
	}

	public String getStatuses() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			staList = statusService.findAllStatus(table.getStartIndex(),
					table.getStartIndex() + table.getRowsPerPage(), table.getSort(), table.getDir().equals("asc"));
			JSONDataTableUtils.setupJSONDataTable(staList, table, statusService.getStatusTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String getTopices() throws Exception{
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			topList = topicService.findAllTopics(table.getStartIndex(),
					table.getStartIndex() + table.getRowsPerPage(), table.getSort(), table.getDir().equals("asc"));
			JSONDataTableUtils.setupJSONDataTable(topList, table, topicService.getTopicTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public IStatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(IStatusService statusService) {
		this.statusService = statusService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}

	public List<Status> getStaList() {
		return staList;
	}

	public void setStaList(List<Status> staList) {
		this.staList = staList;
	}

	public List<Topic> getTopList() {
		return topList;
	}

	public void setTopList(List<Topic> topList) {
		this.topList = topList;
	}
	

}
