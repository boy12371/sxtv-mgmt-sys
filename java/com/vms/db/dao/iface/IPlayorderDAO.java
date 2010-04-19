package com.vms.db.dao.iface;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.vms.common.SearchCondition;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Vediotape;

public interface IPlayorderDAO  extends IBaseRootDAO{
	
	Date getFirstArrangedDate() throws Exception;
	
	List<Playorder> findMonthPlayOrder(Date startTime, Date endTime, int startIndex, int endIndex, boolean ascending) throws Exception;

	void deletePlayorder(List<Playorder> pos,  int userID)throws Exception;
	
	void savePlayorder(List<Playorder> orders, int userID) throws Exception;
	
	List<Playorder> findPlayorderBetweenDateWithFeedback(Date startDate, Date endDate,  int subject) throws Exception;
	
	List<Playorder> findPlayorderByDate(Date date) throws Exception;
	
	List<Vediotape> findVideosByRateOrder(SearchCondition condition)throws Exception; 

}