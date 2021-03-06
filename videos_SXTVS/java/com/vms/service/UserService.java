package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vms.common.BaseException;
import com.vms.common.EncryptUtil;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Employee;
import com.vms.db.bean.Resources;
import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.dao.iface.IRoleDAO;
import com.vms.db.dao.iface.IUserDAO;
import com.vms.service.iface.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	private Class clz = User.class;

	@Override
	public void createUser(User user, List<Integer> roles) throws Exception {
		// TODO Auto-generated method stub
		Set<Role> userRoles = new HashSet<Role>();
		if (roles != null && !roles.isEmpty()) {
			for (int i = 0; i < roles.size(); i++) {
				Role role = (Role) roleDAO.getObject(Role.class, roles.get(i));
				// Role role =new Role();
				// role.setId(roles.get(i));
				userRoles.add(role);
			}
		}
		user.setRoles(userRoles);
		user.setStatus(new Integer(1));
		user.setUserPass(EncryptUtil.encryptString(user.getUserName()));
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
	public List<User> findAllUser(int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<User>) userDAO.findObjectByFields(clz, null, startIndex,
				endIndex, propertyName, ascending);

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

	private boolean resetPassword(int id, String newPwd) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) userDAO.getObject(clz, id);
		user.setUserPass(newPwd == null ? EncryptUtil.encryptString(user
				.getUserName()) : EncryptUtil.encryptString(newPwd));
		userDAO.saveOrUpdateObject(user);
		return true;
	}

	private boolean updateUserRole(int id) throws Exception {
		return false;
	}

	@Override
	public boolean updateUser(String operation, User user, List roleIDs)
			throws Exception {
		// TODO Auto-generated method stub
		if (operation.equals("disableUser")) {
			return disableUser(user.getId());
		} else if (operation.equals("resetPwd")) {
			return resetPassword(user.getId(), null);
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

	public SessionUserInfo authenticate(String username, String password)
			throws Exception {
		SessionUserInfo userInfo = null;
		List<User> users = userDAO.findObjectByField(User.class,
				User.PROP_USER_NAME, username, -1, -1, User.PROP_ID, true);
		if (null == users || 0 == users.size())
			return null;
		User user = users.get(0);
		if (0 == user.getStatus())
			return null;
		if (user.getUserPass().equals(EncryptUtil.encryptString(password))) {
			userInfo = new SessionUserInfo();
			userInfo.setUsername(username);
			userInfo.setPassword(password);
			userInfo.setUserId(user.getId());
			//userInfo.setAuthoritedResource(user.getGrantedResource());
			Iterator<Role> it = user.getRoles().iterator();
			List<Integer> rs = new ArrayList<Integer>();
			String strRoles = "";
			while (it.hasNext()) {
				Role role = it.next();
				rs.add(role.getId());
				strRoles += role.getId() + ",";
			}
			userInfo.setRoles(rs);
			userInfo.setStrRoles(strRoles);
			userInfo.setAuthoritedResource(this.getUserGrantedResource(user.getRoles()));
		}
		return userInfo;
	}

	@Override
	public boolean updateUserPassword(int id, String newPassword)
			throws Exception {
		// TODO Auto-generated method stub
		return this.resetPassword(id, newPassword);
	}

	public User getUserByEmployeeName(String eName) throws Exception {
		List<Employee> employees = userDAO.findObjectByField(Employee.class,
				"name", eName, -1, -1, Employee.PROP_ID, true);
		if (null == employees || 0 == employees.size())
			return null;
		return employees.get(0).getUsers().toArray(new User[0])[0];
	}

	@Override
	public User getUserByUserName(String userName) throws Exception {
		Map<String, Object> propertiesValues = new HashMap<String, Object>();
		propertiesValues.put(User.PROP_USER_NAME, userName);
		User _user = (User) userDAO.getUniqueResultByProperty(clz,
				propertiesValues);
		return _user;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public List<String> getUserGrantedResource(Set<Role> roles) throws Exception {
		// TODO Auto-generated method stub
		List<String> grantedResource = new ArrayList<String>(); 
		Iterator<Role> it = roles.iterator();
		while (it.hasNext()) {
			Role role = it.next();
			Iterator<Resources> rIt  = role.getResources().iterator();
			while (rIt.hasNext()) {
				Resources r = rIt.next();
				if(!grantedResource.contains(r.getUrl())){
					grantedResource.add(r.getUrl());
				}
			}
		}
        return grantedResource;		
	}

}
