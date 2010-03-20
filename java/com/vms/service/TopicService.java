package com.vms.service;

import java.util.List;

import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.dao.iface.ITopicDAO;
import com.vms.service.iface.ITopicService;

public class TopicService implements ITopicService {

	
	private ITopicDAO topicDAO;
	private Class clz = Topic.class;
	@Override
	public void createTopic(Topic topic) throws Exception {
		// TODO Auto-generated method stub
		topic.setStatus(1);
		topicDAO.saveObject(topic);
	}

	@Override
	public void deleteTopic(int id) throws Exception {
		// TODO Auto-generated method stub
		topicDAO.deleteTopic(id);
	}

	@Override
	public void deleteTopic(Topic topic) throws Exception {
		// TODO Auto-generated method stub
		topicDAO.deleteObject(topic);
	}

	@Override
	public List<Topic> findAllTopics(int startIndex, int endIndex, String propertyName, boolean ascending)
			throws Exception {
		// TODO Auto-generated method stub
		
		return this.topicDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
		
	}

	@Override
	public boolean updateTopic(Topic topic) throws Exception {
		// TODO Auto-generated method stub
		Topic top = (Topic) this.topicDAO.getObject(clz, topic.getId());
		top.setTopicName(topic.getTopicName());
		top.setComments(topic.getComments());
		this.topicDAO.saveOrUpdateObject(top);
		return true;
	}
	
	public ITopicDAO getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(ITopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

	@Override
	public int getTopicTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return this.topicDAO.getObjectTotalCount(clz, Topic.PROP_ID);
	
	}

	@Override
	public void disableEnableTopic(int id, boolean enable) throws Exception {
		// TODO Auto-generated method stub
		Topic topic = (Topic) this.topicDAO.getObject(clz, id);
		topic.setStatus(enable?1:0);
		this.topicDAO.saveOrUpdateObject(topic);		
	}



}
