package com.vms.service;


import com.vms.db.bean.UserRole;
import com.vms.db.dao.iface.IUserRoleDAO;
import com.vms.service.iface.IUserRoleService;

public class UserRoleService implements IUserRoleService {
	private IUserRoleDAO userRoleDAO;
	private Class clz = UserRole.class;

	@Override
	public void addRoleForUser(UserRole ur) throws Exception {
		// TODO Auto-generated method stub
		userRoleDAO.saveObject(ur);
	}

	@Override
	public void removeRoleFromUser(UserRole ur) throws Exception {
		// TODO Auto-generated method stub
		userRoleDAO.deleteObject(ur);
	}

	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

}
