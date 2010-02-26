package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Playorder;

public interface IPlayorderDAO  extends IBaseRootDAO{
	
	Date getFirstArrangedDate() throws Exception;
	
	List<Playorder> findMonthPlayOrder(Date startTime, Date endTime, int startIndex, int endIndex, boolean ascending) throws Exception;

	void deletePlayorder(List<Playorder> pos,  int userID)throws Exception;
	
	void savePlayorder(List<Playorder> orders, int userID) throws Exception;
	
	List findPlayorderByMonth(Date date)throws Exception;

}