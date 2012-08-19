package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;

public interface IEmployeeDAO  extends IBaseRootDAO{
	
	void deleteEmployee(Class clz, int id)throws Exception;
	
	List<Employee> findAllEmployees(Class clz, int startIndex,int endIndex) throws Exception;
	
	/***
	 * 更新员工信息 
	 * @param voEmp 仅跟新员工信息，不更新其user
	 * @throws Exception
	 */
	boolean updateEmployee(EmployeeVO voEmp) throws Exception;
	
}