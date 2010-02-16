package com.vms.service.iface;

import java.util.Date;
import java.util.List;

import com.vms.common.SessionUserInfo;

public interface IPlayorderService {
	
	void savePlayorder(String orderString,SessionUserInfo user)throws Exception;

	List findPlayorderByMonth(Date month)throws Exception;
	
	boolean changePlayeOrder(int orderID, Date fromDate, Date toDate,SessionUserInfo user)throws Exception;
}
