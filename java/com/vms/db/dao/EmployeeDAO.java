package com.vms.db.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.db.bean.Employee;
import com.vms.db.dao.iface.IEmployeeDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class EmployeeDAO extends com.vms.db.dao.BaseRootDAO implements
		IEmployeeDAO {
	@Override
	public void deleteEmployee(Class clz, int id) throws Exception {
		// TODO Auto-generated method stub
		this.deleteObject(this.loadObject(clz, id));
	}

	@Override
	public List<Employee> findAllEmployees(Class clz, int startIndex,
			int endIndex) throws Exception {
		// TODO Auto-generated method stub

		return (List<Employee>) this.findObjectByFields(clz, null, startIndex,
				endIndex, null, false);
	}

}