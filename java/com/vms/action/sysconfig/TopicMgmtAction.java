package com.vms.action.sysconfig;

import com.vms.common.BaseAction;
import com.vms.db.bean.Topic;
import com.vms.service.iface.ITopicService;

public class TopicMgmtAction extends BaseAction {
	
	private ITopicService topicService;
	
	private Topic topic;
	
	
	public String toAddTopic(){
		return this.SUCCESS;
	} 

	public String doAddTopic() throws Exception{
		topicService.createTopic(topic);
		return this.SUCCESS;
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
	
}
