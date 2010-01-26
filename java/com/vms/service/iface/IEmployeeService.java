package com.vms.service.iface;

import java.util.List;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;

public interface IEmployeeService {

	void deleteEmployee(int id) throws Exception;

	void deleteEmployee(Employee employee) throws Exception;

	List<Employee> findAllEmployees(int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception;

	void createEmployee(Employee employee) throws Exception;

	Employee getEmployeeById(int id) throws Exception;

	int getEmployeeTotalCount() throws Exception;

	/***
	 * 更新员工信息 
	 * @param voEmp 仅跟新员工信息，不更新其user
	 * @throws Exception
	 */
	boolean updateEmployee(EmployeeVO voEmp) throws Exception;
}
