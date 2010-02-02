package com.vms.service;

import java.util.List;

import com.vms.db.bean.Role;
import com.vms.db.dao.iface.IRoleDAO;
import com.vms.service.iface.IRoleService;

public class RoleService implements IRoleService {
	
	private IRoleDAO roleDAO;
	private Class clz = Role.class;

	@Override
	public List<Role> findAllRoles(int startIndex, int endIndex, String propertyName,
			boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<Role>)roleDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);

	}

	@Override
	public List<Role> findAllRoles() throws Exception {
		// TODO Auto-generated method stub
		return (List<Role>)roleDAO.findAll(clz);
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
