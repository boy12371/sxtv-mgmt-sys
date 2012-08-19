package com.vms.service.iface;

import java.util.Date;
import java.util.List;

import com.vms.beans.AccuracyVO;
import com.vms.db.bean.Subject;

public interface IAccuracyService {
	public List<AccuracyVO> findAllAccuracy(Date startDate, Date endDate, int subject) throws Exception;
	
	public List<Subject> getAllSubjects() throws Exception;
}
