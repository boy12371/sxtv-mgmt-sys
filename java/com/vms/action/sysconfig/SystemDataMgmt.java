package com.vms.action.sysconfig;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.ISubjectService;
import com.vms.service.iface.ITopicService;

public class SystemDataMgmt extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SystemDataMgmt.class);
	private IStatusService statusService;
	private ITopicService topicService;
	private ISubjectService subjectService;

	private JSONDataTable table;
	private Subject subject;
	private Topic topic;
	private boolean enableOperator;

	public String toSystemData() {
		return this.SUCCESS;
	}

	public String getStatuses() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Status> staList = statusService.findAllStatus(table
					.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir()
					.equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(staList, table, statusService
					.getStatusTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String getTopices() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Topic> topList = topicService.findAllTopics(table
					.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir()
					.equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(topList, table, topicService
					.getTopicTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String getSubjects() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Subject> subList = subjectService.findAllSubjects(table
					.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir()
					.equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(subList, table,
					subjectService.getSubjectTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	
	public String doDisableEnableSubject() throws Exception{
		try {
			this.subjectService.disableEnable(subject.getId(), enableOperator);
			this.addActionMessage("栏目已禁用");
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
		}
		this.addActionError("操作失败");
		return INPUT;
	}
	
	public String modifySubject() throws Exception{
		try {
			this.subjectService.updateSubject(subject);
			this.addActionMessage("修改成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("修改失败");
		return INPUT;
	}
	
	public String modifyTopic()throws Exception{
		try {
			this.topicService.updateTopic(topic);
			this.addActionMessage("修改成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("修改失败");
		return INPUT;
	}
	
	public String doDisableEnableTopic()throws Exception{
		try {
			topicService.disableEnableTopic(topic.getId(), enableOperator);
			this.addActionMessage("题材已禁用");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("操作失败");
		return INPUT;
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

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public boolean isEnableOperator() {
		return enableOperator;
	}

	public void setEnableOperator(boolean enableOperator) {
		this.enableOperator = enableOperator;
	}

}
