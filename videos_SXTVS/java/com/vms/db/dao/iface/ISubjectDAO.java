package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Subject;

public interface ISubjectDAO  extends IBaseRootDAO{
	
	void deleteSubject(int subjectID)throws Exception;
	
	List<Subject> findAllSubject(int startIndex, int endIndex)throws Exception;


}