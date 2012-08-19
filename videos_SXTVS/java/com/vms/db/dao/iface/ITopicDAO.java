package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.vms.db.bean.Topic;

public interface ITopicDAO  extends IBaseRootDAO{
	
	
	void deleteTopic(int id)throws Exception;
	
	List<Topic> findAllTopics(int startIndex, int endIndex)throws Exception;
	
	boolean updateTopic(Topic topic) throws Exception;
	
	
	
}