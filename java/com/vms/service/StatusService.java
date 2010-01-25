package com.vms.service;

import java.util.List;

import com.vms.db.bean.Status;
import com.vms.db.dao.iface.IStatusDAO;
import com.vms.service.iface.IStatusService;

public class StatusService implements IStatusService {

	
	private IStatusDAO statusDAO;
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
	public List<Status> findAllStatus(int startIndex,int endIndex) throws Exception {
		// TODO Auto-generated method stub
		return statusDAO.findAllStatus(startIndex, endIndex);
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

}
