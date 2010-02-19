package com.vms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.dao.iface.IUserDAO;
import com.vms.service.iface.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;
	private Class clz = User.class;

	@Override
	public void createUser(User user, List<Integer> roles) throws Exception {
		// TODO Auto-generated method stub
		Set<Role> userRoles = new HashSet<Role>();
		if(roles!=null && !roles.isEmpty()){
			for (int i = 0; i < roles.size(); i++) {
				Role role =new Role();
				role.setId(roles.get(i));
				userRoles.add(role);
			}
		}
		user.setRoles(userRoles);		
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
	public List<User> findAllUser(int startIndex, int endIndex, String propertyName, boolean ascending)
			throws Exception {
		// TODO Auto-generated method stub
		return (List<User>) userDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);

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

	private boolean disableUser(int id) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update User u set u.status=0 where u.id=?";
		return userDAO.updateUser(hql, new Object[] { id });

	}

	private boolean enableUser(int id) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update User u set u.status=1 where u.id=?";
		return userDAO.updateUser(hql, new Object[] { id });
	}

	private boolean resetPassword(int id, String password) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update User u set u.userPass = ? where u.id=?";
		return userDAO.updateUser(hql, new Object[] { password, id });

	}

	private boolean updateUserRole(int id) throws Exception {
		return false;
	}

	@Override
	public boolean updateUser(String operation, User user, List roleIDs) throws Exception {
		// TODO Auto-generated method stub
		if (operation.equals("updateUserRole")) {
			return updateUserRole(user.getId());
		} else if (operation.equals("disableUser")) {
			return disableUser(user.getId());
		} else if (operation.equals("resetPwd")) {
			return resetPassword(user.getId(), "123456");
		} else {
			return enableUser(user.getId());
		}
	}

	// @Override
	// public boolean updateUserInfo(User user) throws Exception {
	// // TODO Auto-generated method stub
	// String hql ="update User u set u.userName=?, u. where u.id=?";
	// return userDAO.updateUser(hql, new Object[]{id});
	// }

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public SessionUserInfo authenticate(String username, String password) throws Exception{
		SessionUserInfo userInfo = null;
		List<User> users = userDAO.findObjectByField(User.class, "userName", username, -1, -1, User.PROP_ID, true);
		if(null == users || 0 == users.size()) return null;
		User user = users.get(0);
		if(0 == user.getStatus()) return null;
		if(user.getUserPass().equals(password)){
			userInfo = new SessionUserInfo();
			userInfo.setUsername(username);
			userInfo.setPassword(password);
			userInfo.setUserId(user.getId());
		}
		return userInfo;
	}

}
