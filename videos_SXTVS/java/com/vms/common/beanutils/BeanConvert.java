package com.vms.common.beanutils;

import java.util.ArrayList;
import java.util.List;

import com.vms.beans.EmployeeVO;
import com.vms.db.bean.Employee;

public class BeanConvert {

	/****
	 * 将后台的employee 对象转换前台显示的对象(不包含user)
	 * 
	 * @param emp
	 *            需要转换的后台对象
	 * @return
	 */
	public static EmployeeVO convertBean(Employee emp) {

		if (emp != null) {
			EmployeeVO vEmp = new EmployeeVO();
			vEmp.setId(emp.getId());
			vEmp.setName(emp.getName());
			vEmp.setGender(emp.getGender());
			vEmp.setBirthday(emp.getBirthday());
			vEmp.setContractDate(emp.getContractDate());
			vEmp.setTel(emp.getTel());
			vEmp.setComments(emp.getComments());
			return vEmp;
		}
		return null;

	}

	public static List<EmployeeVO> convertBeans(List<Employee> empList) {
		List<EmployeeVO> vEmps = new ArrayList<EmployeeVO>();
		if (!empList.isEmpty()) {
			for(Employee e :empList){
				vEmps.add(convertBean(e));	
			}			
		}
		return vEmps;

	}
	
	
}
