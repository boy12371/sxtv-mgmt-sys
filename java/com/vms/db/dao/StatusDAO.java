package com.vms.db.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.db.bean.Status;
import com.vms.db.dao.iface.IStatusDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class StatusDAO extends com.vms.db.dao.BaseRootDAO  implements IStatusDAO {

	private Class clz = com.vms.db.bean.Status.class;

	@Override
	public void deleteStatus(int id) throws Exception {
		// TODO Auto-generated method stub
		
		this.delete(this.load(clz, id));
		
	}

	@Override
	public List<Status> findAllStatus(int startIndex, int endIndex)
			throws Exception {
		// TODO Auto-generated method stub
		
		return (List<Status>)this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
		
	}

	
}