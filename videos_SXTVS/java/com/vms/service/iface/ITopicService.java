package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;

public interface ITopicService {

	List<Topic> findAllTopics(int startIndex, int endIndex, String propertyName, boolean ascending,boolean enabled) throws Exception;

	void deleteTopic(int id) throws Exception;

	void deleteTopic(Topic topic) throws Exception;

	void createTopic(Topic topic) throws Exception;
	
	boolean updateTopic(Topic topic) throws Exception;
	
	void disableEnableTopic(int id, boolean enable)throws Exception;
	
	int getTopicTotalCount() throws Exception;
}
