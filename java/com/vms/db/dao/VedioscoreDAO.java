package com.vms.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.bean.UserRole;
import com.vms.db.dao.iface.IVedioscoreDAO;


/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class VedioscoreDAO extends com.vms.db.dao.BaseRootDAO implements IVedioscoreDAO {

	public static Class clz = com.vms.db.bean.Vedioscore.class;	
	
	public List<User> findAllExaminer() throws Exception{
		List<User> users = new ArrayList<User>();
		List<UserRole> urs = this.findObjectByField(UserRole.class, "roleid", new Role(3), -1, -1, "roleid", true);
		for(UserRole ur:urs){
			users.add(ur.getUserid());
		}
		return users;
	}
}