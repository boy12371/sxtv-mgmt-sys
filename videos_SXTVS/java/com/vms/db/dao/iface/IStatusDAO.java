package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Status;

public interface IStatusDAO extends IBaseRootDAO {

	void deleteStatus(int id) throws Exception;
	
	List<Status> findAllStatus(int startIndex, int endIndex)throws Exception;


}