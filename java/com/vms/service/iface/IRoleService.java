package com.vms.service.iface;
import java.util.List;

import com.vms.db.bean.Role;
public interface IRoleService {

	
	List<Role> findAllRoles(int startIndex, int endIndex,
			String propertyName, boolean ascending)throws Exception;
	
	List<Role> findAllRoles() throws Exception;
}
