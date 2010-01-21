package com.vms.service;

import java.util.List;

import com.vms.db.bean.Company;
import com.vms.db.dao.iface.ICompanyDAO;
import com.vms.service.iface.ICompanyService;

public class CompanyService implements ICompanyService {

	private ICompanyDAO companyDAO;
	
	@Override
	public void deleteCompany(int id) throws Exception {
		// TODO Auto-generated method stub
		
		this.companyDAO.deleteCompany(id);

	}

	@Override
	public void deleteCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		this.companyDAO.delete(company);
	}

	@Override
	public List<Company> findAllCompany(int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub

		return this.companyDAO.findAllCompanies(startIndex, endIndex);
	}

	@Override
	public void createCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		this.companyDAO.save(company);
	}

	public ICompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

}
