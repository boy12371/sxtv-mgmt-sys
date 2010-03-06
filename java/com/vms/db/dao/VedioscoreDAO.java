package com.vms.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.dao.iface.IVedioscoreDAO;


/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class VedioscoreDAO extends com.vms.db.dao.BaseRootDAO implements IVedioscoreDAO {

	public static Class clz = com.vms.db.bean.Vedioscore.class;	
	
	public List<User> findAllExaminer() throws Exception{
		List<User> users;
		users = findAll(User.class);
		if(null == users || 0 == users.size()){
			users = new ArrayList<User>();
		}
		return users;
	}
}