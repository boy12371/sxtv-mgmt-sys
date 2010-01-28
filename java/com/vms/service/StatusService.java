package com.vms.service;

import java.util.List;

import com.vms.db.bean.Status;
import com.vms.db.dao.iface.IStatusDAO;
import com.vms.service.iface.IStatusService;

public class StatusService implements IStatusService {

	
	private IStatusDAO statusDAO;
	private Class clz= Status.class;
	@Override
	public void deleteStatus(int id) throws Exception {
		// TODO Auto-generated method stub
		
		statusDAO.deleteStatus(id);
		
	}

	@Override
	public void deleteStatus(Status status) throws Exception {
		// TODO Auto-generated method stub
		statusDAO.deleteObject(status);
	}

	@Override
	public List<Status> findAllStatus(int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		return (List<Status>)statusDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
	}

	@Override
	public void createStatus(Status status) throws Exception {
		// TODO Auto-generated method stub
		this.statusDAO.saveObject(status);
	}

	public IStatusDAO getStatusDAO() {
		return statusDAO;
	}

	public void setStatusDAO(IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	@Override
	public int getStatusTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return this.statusDAO.getObjectTotalCount(clz, Status.PROP_ID);
	}

}
