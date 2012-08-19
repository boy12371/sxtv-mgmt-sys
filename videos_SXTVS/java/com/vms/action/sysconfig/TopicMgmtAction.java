package com.vms.action.sysconfig;

import org.apache.log4j.Logger;

import com.vms.common.BaseAction;
import com.vms.db.bean.Topic;
import com.vms.service.iface.ITopicService;

public class TopicMgmtAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5505272038400424322L;
	private static Logger logger = Logger.getLogger(Topic.class);
	private ITopicService topicService;
	private Topic topic;
	private boolean enableOperator;
	
	public String toAddTopic(){
		return this.SUCCESS;
	} 

	public String doAddTopic() throws Exception{
		try {
			topicService.createTopic(topic);
			this.addActionMessage("添加成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("添加失败");
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
	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public boolean isEnableOperator() {
		return enableOperator;
	}

	public void setEnableOperator(boolean enableOperator) {
		this.enableOperator = enableOperator;
	}
	
}
