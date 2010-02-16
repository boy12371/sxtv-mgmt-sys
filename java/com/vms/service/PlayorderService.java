package com.vms.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.vms.common.SessionUserInfo;
import com.vms.db.dao.PlayorderDAO;
import com.vms.db.dao.iface.IPlayorderDAO;
import com.vms.service.iface.IPlayorderService;

public class PlayorderService implements IPlayorderService {

	private IPlayorderDAO  playorderDAO;
	
	@Override
	public void savePlayorder(String orderString,SessionUserInfo user) throws Exception {
		// TODO Auto-generated method stub
		if (orderString != null && orderString.length() > 0) {
			String[] orderList = orderString.split(";");
			for (int i = 0; i < orderList.length; i++) {
				String string = orderList[i];
				System.out.println(string);
				// String[] oString = StringUtils.split("=");

			}

		}

	}

	

	@Override
	public boolean changePlayeOrder(int orderID, Date fromDate, Date toDate,
			SessionUserInfo user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findPlayorderByMonth(Date month) throws Exception {
		// TODO Auto-generated method stub
		return playorderDAO.findPlayorderByMonth(month);
		
	}



	public IPlayorderDAO getPlayorderDAO() {
		return playorderDAO;
	}



	public void setPlayorderDAO(IPlayorderDAO playorderDAO) {
		this.playorderDAO = playorderDAO;
	}

}
