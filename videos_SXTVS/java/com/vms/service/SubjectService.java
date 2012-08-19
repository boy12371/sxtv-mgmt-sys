package com.vms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Subject;
import com.vms.db.dao.iface.ISubjectDAO;
import com.vms.service.iface.ISubjectService;

public class SubjectService implements ISubjectService {

	
	private ISubjectDAO subjectDAO;
	private Class clz = Subject.class;
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
		subject.setStatus(1);
		subjectDAO.saveObject(subject);
	}



	@Override
	public List<Subject> findAllSubjects(int startIndex, int endIndex,
			String propertyName, boolean ascending,boolean enabled) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> propertiesValues = null;
		if (enabled) {
			propertiesValues = new HashMap<String, Object>();
			propertiesValues.put(Subject.PROP_STATUS, new Integer(1));
		}
		return (List<Subject>)subjectDAO.findObjectByFields(clz, propertiesValues, startIndex, endIndex, propertyName, ascending);
	}

	@Override
	public int getSubjectTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return this.subjectDAO.getObjectTotalCount(clz, Subject.PROP_ID);
	}

	public ISubjectDAO getSubjectDAO() {
		return subjectDAO;
	}



	public void setSubjectDAO(ISubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}



	@Override
	public boolean updateSubject(Subject sub) throws Exception {
		// TODO Auto-generated method stub
		Subject subject = (Subject) this.subjectDAO.getObject(clz, sub.getId());
		subject.setSubjectName(sub.getSubjectName());
		subject.setComments(sub.getComments());
		this.subjectDAO.saveOrUpdateObject(subject);
		return true;
	}



	@Override
	public void disableEnable(int id, boolean enable) throws Exception {
		// TODO Auto-generated method stub
		Subject subject = (Subject) this.subjectDAO.getObject(clz, id);
		subject.setStatus(enable?1:0);
		this.subjectDAO.saveOrUpdateObject(subject);
	}



	

}
