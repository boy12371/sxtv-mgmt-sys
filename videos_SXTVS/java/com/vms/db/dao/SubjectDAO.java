package com.vms.db.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.db.bean.Subject;
import com.vms.db.dao.iface.ISubjectDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class SubjectDAO extends com.vms.db.dao.BaseRootDAO  implements ISubjectDAO{

	private Class clz = com.vms.db.bean.Subject.class;

	@Override
	public void deleteSubject(int subjectID) throws Exception {
		// TODO Auto-generated method stub
		this.deleteObject(this.loadObject(clz, subjectID));
		
	}

	@Override
	public List<Subject> findAllSubject(int startIndex, int endIndex)
			throws Exception {
		// TODO Auto-generated method stub
		
		this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
		return null;
	}

	
}