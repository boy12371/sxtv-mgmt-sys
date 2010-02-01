package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;

public interface IVediotapeDAO extends IBaseRootDAO {
	
	List<Vediotape> findVedioesByStatus(Status status ,int startIndex, int endIndex) throws Exception;
	
	public int getVedioTotalCountByStatus(Status status) throws Exception;
}