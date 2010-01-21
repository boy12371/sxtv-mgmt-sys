package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;

public interface ITopicService {

	List<Topic> findAllTopics(int startIndex,int endIndex) throws Exception;

	void deleteTopic(int id) throws Exception;

	void deleteTopic(Topic topic) throws Exception;

	void createTopic(Topic topic) throws Exception;

}
