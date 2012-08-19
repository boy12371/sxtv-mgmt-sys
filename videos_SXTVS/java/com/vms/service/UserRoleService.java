package com.vms.service;


import java.util.List;

import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.bean.UserRole;
import com.vms.db.dao.UserRoleDAO;
import com.vms.db.dao.iface.IRoleDAO;
import com.vms.db.dao.iface.IUserRoleDAO;
import com.vms.service.iface.IUserRoleService;

public class UserRoleService implements IUserRoleService {
	private IUserRoleDAO userRoleDAO;
	private IRoleDAO roleDAO;
	private Class clz = UserRole.class;

	@Override
	public void addRoleForUser(UserRole ur) throws Exception {
		// TODO Auto-generated method stub
		userRoleDAO.saveObject(ur);
	}

	@Override
	public void removeRolesFromUser(User user) throws Exception {
		// TODO Auto-generated method stub
		List<UserRole> uRoles = (List<UserRole>)userRoleDAO.findObjectByField(clz, UserRole.PROP_USERID, user, -1, -1, UserRole.PROP_ID, true);
		for (UserRole userRole : uRoles) {
			this.userRoleDAO.deleteObject(userRole);
		}
	}

	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void updateRolesForUser(User user, List<Integer> roles)
			throws Exception {
		// TODO Auto-generated method stub
		for (Integer integer : roles) {
			UserRole ur = new UserRole();
			ur.setRoleid((Role) roleDAO.getObject(Role.class, integer));
			ur.setUserid(user);
			userRoleDAO.saveObject(ur);
		}
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
