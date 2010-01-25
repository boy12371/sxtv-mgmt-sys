package com.vms.service;

import java.util.List;

import com.vms.db.bean.User;
import com.vms.db.dao.iface.IUserDAO;
import com.vms.service.iface.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;

	@Override
	public void createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userDAO.saveObject(user);
	}

	@Override
	public void deleteUser(int id) throws Exception {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
	}

	@Override
	public void deleteUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userDAO.deleteObject(user);
	}

	@Override
	public List findAllUser(int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub		
		return userDAO.findAllUser(startIndex, endIndex);
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
