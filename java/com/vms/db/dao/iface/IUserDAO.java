package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.vms.db.bean.User;

public interface IUserDAO  extends IBaseRootDAO{
	
	void deleteUser(int id)throws Exception;
	
	List<User> findAllUser(int startIndex, int endIndex)throws Exception;
	
	boolean updateUser(String hql,Object[] args) throws Exception;
}