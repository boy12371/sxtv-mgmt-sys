package com.vms.service.iface;

import java.util.List;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;

public interface IEmployeeService {

	void deleteEmployee(int id) throws Exception;

	void deleteEmployee(Employee employee) throws Exception;

	/***
	 * 查询所有员工 分页查询
	 * @param startIndex 
	 * @param endIndex
	 * @param propertyName  排序列 
	 * @param ascending  true=升序，false=降序
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 查询所有员工 不分页
	 * @return
	 * @throws Exception
	 */
	List<Employee> findAllEmployees() throws Exception;
	
	List<Employee> findAllUnassignedEmployees() throws Exception;
	

	
	/***
	 * 注销或启用员工， 此员工关联系统用户将同时禁用或启用.
	 * @param id 
	 * @param isEnable true=Enable false=Disable 
	 * @return
	 * @throws Exception
	 */
	boolean employeeEnableOrDisable(int id, boolean isEnable) throws Exception;
}
