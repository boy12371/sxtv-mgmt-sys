package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Employee;

public interface IEmployeeDAO  extends IBaseRootDAO{
	
	void deleteEmployee(int id)throws Exception;
	
	List<Employee> findAllEmployees(int startIndex,int endIndex) throws Exception;
	
}