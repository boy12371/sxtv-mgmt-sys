package com.vms.service;

import java.util.List;

import com.vms.db.bean.Resources;
import com.vms.db.dao.iface.IResourcesDAO;
import com.vms.service.iface.IResourceService;

public class ResourceService implements IResourceService{

	private IResourcesDAO resourceDAO; 
	private Class clz = Resources.class;
	
	@Override
	public List<Resources> findAllResources() throws Exception {
		// TODO Auto-generated method stub
		return (List<Resources>)resourceDAO.findAll(clz);		
	}

	public IResourcesDAO getResourceDAO() {
		return resourceDAO;
	}

	public void setResourceDAO(IResourcesDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

}
