package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.User;

public interface IUserService {
	
	void deleteUser(int id)throws Exception;
	void deleteUser(User user)throws Exception;
	List findAllUser(int startIndex,int endIndex) throws Exception;
	void createUser(User user)throws Exception;
	
	
	

}
