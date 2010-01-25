package com.vms.db.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.db.bean.Topic;
import com.vms.db.dao.iface.ITopicDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class TopicDAO extends com.vms.db.dao.BaseRootDAO  implements ITopicDAO{

	private Class clz = com.vms.db.bean.Topic.class;

	@Override
	public void deleteTopic(int id) throws Exception {
		// TODO Auto-generated method stub
		this.deleteObject(this.loadObject(clz, id));
	}

	@Override
	public List<Topic> findAllTopics(int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub
		
		return (List<Topic>)this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
	
	}

	
	
	
}