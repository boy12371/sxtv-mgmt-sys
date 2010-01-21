package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Company;


public interface ICompanyService {

	
	void deleteCompany(int id)throws Exception;
	void deleteCompany(Company company)throws Exception;
	List<Company> findAllCompany(int startIndex,int endIndex)throws Exception;

	void createCompany(Company company)throws Exception;
	
}
