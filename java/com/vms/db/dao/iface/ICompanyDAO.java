package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;

import com.vms.db.bean.Company;


public interface ICompanyDAO  extends IBaseRootDAO {
	

	void deleteCompany(int id)throws Exception;
	
	List<Company> findAllCompanies(int startIndex, int endIndex)throws Exception;
	
	/**
	 * 更新公司信息 不级联更新
	 * @param company
	 * @return
	 * @throws Exception
	 */
	boolean updateCompany(Company company) throws Exception;
}