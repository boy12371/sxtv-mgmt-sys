package com.vms.service;

import java.util.List;

import com.vms.db.bean.User;
import com.vms.db.dao.iface.IUserDAO;
import com.vms.service.iface.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;
	private Class clz = User.class;

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
	public List<User> findAllUser(int startIndex, int endIndex, String propertyName,
			boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<User>)userDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
		
	}

	@Override
	public User getUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		return (User) userDAO.getObject(clz, id);
	}

	@Override
	public int getUserTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getObjectTotalCount(clz, User.PROP_ID);
	}

	@Override
	public boolean disableUser(int id) throws Exception {
		// TODO Auto-generated method stub
		String hql ="update User u set u.status=0 where u.id=?";
		return userDAO.updateUser(hql, new Object[]{id});
		
	}

	@Override
	public boolean enableUser(int id) throws Exception {
		// TODO Auto-generated method stub
		String hql ="update User u set u.status=1 where u.id=?";
		return userDAO.updateUser(hql, new Object[]{id});
	}

	@Override
	public boolean resetPassword(int id, String password) throws Exception {
		// TODO Auto-generated method stub
		String hql ="update User u set u.userPass = ? where u.id=?";
		return userDAO.updateUser(hql, new Object[]{password,id});
		
	}

//	@Override
//	public boolean updateUserInfo(User user) throws Exception {
//		// TODO Auto-generated method stub
//		String hql ="update User u set u.userName=?, u. where u.id=?";
//		return userDAO.updateUser(hql, new Object[]{id});
//	}
	

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
