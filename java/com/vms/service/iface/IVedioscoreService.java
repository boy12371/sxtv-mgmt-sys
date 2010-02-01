package com.vms.service.iface;

import java.util.List;

import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Status;

public interface IVedioscoreService {
	
	public List<VedioTapeVO> getAllUnExaminedVedioes(int startIndex, int endIndex) throws Exception;
	
	public List<VedioTapeVO> getAllExaminedVedioes(int startIndex, int endIndex) throws Exception;
	
	public int getVedioTotalCountByStatus(Status status) throws Exception;
}
