package com.vms.db.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.db.bean.User;
import com.vms.db.dao.iface.IUserDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class UserDAO  extends com.vms.db.dao.BaseRootDAO  implements IUserDAO {

	private Class clz = com.vms.db.bean.User.class;

	@Override
	public void deleteUser(int id) throws Exception {
		// TODO Auto-generated method stub
		this.delete(this.load(clz, id));
	}

	@Override
	public List<User> findAllUser(int startIndex, int endIndex)
			throws Exception {
		// TODO Auto-generated method stub
		
		return (List<User>)this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
	}
}