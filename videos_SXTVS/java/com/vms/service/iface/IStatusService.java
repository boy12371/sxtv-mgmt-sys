package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Status;

public interface IStatusService {
	
	void deleteStatus(int id)throws Exception;
	void deleteStatus(Status status)throws Exception;
	List<Status> findAllStatus(int startIndex, int endIndex, String propertyName, boolean ascending)throws Exception;
	
	void createStatus(Status status)throws Exception;
	
	int getStatusTotalCount() throws Exception;
}
