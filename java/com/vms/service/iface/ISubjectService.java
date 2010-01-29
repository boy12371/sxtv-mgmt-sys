package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Subject;

public interface ISubjectService {

	void deleteSubject(int id) throws Exception;

	void deleteSubject(Subject subject) throws Exception;

	void createSubject(Subject subject) throws Exception;

	List<Subject> findAllSubjects(int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception;

	int getSubjectTotalCount() throws Exception;
}
