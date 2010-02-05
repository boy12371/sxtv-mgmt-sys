package com.vms.service.iface;

import com.vms.db.bean.UserRole;

public interface IUserRoleService {

	/**
	 * 添加用户权限
	 * @param ur
	 * @throws Exception
	 */
	void addRoleForUser(UserRole ur)throws Exception;
	
	void removeRoleFromUser(UserRole ur) throws Exception;
	
	
}
