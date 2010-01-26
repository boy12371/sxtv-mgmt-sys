package com.vms.db.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;
import com.vms.db.dao.iface.IEmployeeDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class EmployeeDAO extends com.vms.db.dao.BaseRootDAO implements IEmployeeDAO {
	@Override
	public void deleteEmployee(Class clz, int id) throws Exception {
		// TODO Auto-generated method stub
		this.deleteObject(this.loadObject(clz, id));
	}

	@Override
	public List<Employee> findAllEmployees(Class clz, int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub

		return (List<Employee>) this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
	}

	/***
	 * 更新员工信息 
	 * @param voEmp 仅跟新员工信息，不更新其user
	 * @throws Exception
	 */
	@Override
	public boolean updateEmployee(EmployeeVO voEmp) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Employee emp set emp.name=?, emp.birthday=?, emp.contractDate=?, emp.gender=?, emp.tel=?, emp.comments=? where emp.id=?";
		int result = this.getHibernateTemplate().bulkUpdate(
				hql,
				new Object[] { voEmp.getName(), voEmp.getBirthday(), voEmp.getContractDate(), voEmp.getGender(),
						voEmp.getTel(), voEmp.getComments(), voEmp.getId() });
		
		return result!=0;
	}
}