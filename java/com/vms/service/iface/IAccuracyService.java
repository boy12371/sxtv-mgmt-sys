package com.vms.service.iface;

import java.util.Date;
import java.util.List;

import com.vms.beans.AccuracyVO;

public interface IAccuracyService {
	public List<AccuracyVO> findAllAccuracy(Date startDate, Date endDate) throws Exception;
}
