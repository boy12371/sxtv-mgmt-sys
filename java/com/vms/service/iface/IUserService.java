package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.User;

public interface IUserService {

	void deleteUser(int id) throws Exception;

	void deleteUser(User user) throws Exception;

	List<User> findAllUser(int startIndex, int endIndex, String propertyName,
			boolean ascending) throws Exception;

	void createUser(User user) throws Exception;

	User getUserById(int id) throws Exception;

	int getUserTotalCount() throws Exception;
	
	boolean updateUser(String operation, User user)throws Exception;

}
