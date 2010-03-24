package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.User;
import com.vms.db.bean.UserRole;

public interface IUserRoleService {

	/**
	 * 添加用户权限
	 * @param ur
	 * @throws Exception
	 */
	void addRoleForUser(UserRole ur)throws Exception;
	
	void removeRoleFromUser(UserRole ur) throws Exception;
	
	void updateRolesForUser(User user, List<Integer> roles)throws Exception;
	
}
