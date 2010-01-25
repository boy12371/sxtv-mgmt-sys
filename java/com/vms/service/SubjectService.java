package com.vms.service;

import java.util.List;

import com.vms.db.bean.Subject;
import com.vms.db.dao.iface.ISubjectDAO;
import com.vms.service.iface.ISubjectService;

public class SubjectService implements ISubjectService {

	
	private ISubjectDAO subjectDAO;
	@Override
	public void deleteSubject(int id) throws Exception {
		// TODO Auto-generated method stub
		subjectDAO.deleteSubject(id);	

	}



	@Override
	public void deleteSubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		subjectDAO.deleteObject(subject);
	}

	@Override
	public void createSubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		subjectDAO.saveObject(subject);
	}



	@Override
	public List<Subject> findAllSubjects(int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub
		return subjectDAO.findAllSubject(startIndex, endIndex);
	}



	public ISubjectDAO getSubjectDAO() {
		return subjectDAO;
	}



	public void setSubjectDAO(ISubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

}
