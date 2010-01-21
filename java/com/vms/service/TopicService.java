package com.vms.service;

import java.util.List;

import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.dao.iface.ITopicDAO;
import com.vms.service.iface.ITopicService;

public class TopicService implements ITopicService {

	
	private ITopicDAO topicDAO;
	@Override
	public void createTopic(Topic topic) throws Exception {
		// TODO Auto-generated method stub
		topicDAO.save(topic);
	}

	@Override
	public void deleteTopic(int id) throws Exception {
		// TODO Auto-generated method stub
		topicDAO.deleteTopic(id);
	}

	@Override
	public void deleteTopic(Topic topic) throws Exception {
		// TODO Auto-generated method stub
		topicDAO.delete(topic);
	}

	@Override
	public List<Topic> findAllTopics(int startIndex, int endIndex)
			throws Exception {
		// TODO Auto-generated method stub
		
		return this.topicDAO.findAllTopics(startIndex, endIndex);
		
	}

	public ITopicDAO getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(ITopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

}
