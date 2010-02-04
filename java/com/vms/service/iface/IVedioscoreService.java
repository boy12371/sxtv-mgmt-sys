package com.vms.service.iface;

import java.util.List;

import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Status;

public interface IVedioscoreService {
	
	public List<VedioTapeVO> getAllUnExaminedVedioes(int startIndex, int endIndex) throws Exception;
	
	public List<VedioScoreVO> getUserExaminedVedioes(String username,int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception;
	
	public int getVedioCountByStatus(Status status) throws Exception;
	
	public int getCountOfUserExaminedVedio(String username) throws Exception;
}
