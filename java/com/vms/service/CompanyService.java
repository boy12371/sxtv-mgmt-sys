package com.vms.service;

import java.util.List;

import com.vms.db.bean.Company;
import com.vms.db.dao.iface.ICompanyDAO;
import com.vms.service.iface.ICompanyService;

public class CompanyService implements ICompanyService {

	private ICompanyDAO companyDAO;
	private Class clz = Company.class;

	@Override
	public void createCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		companyDAO.saveObject(company);
	}

	@Override
	public void deleteCompany(int id) throws Exception {
		// TODO Auto-generated method stub
		companyDAO.deleteCompany(id);
	}

	@Override
	public void deleteCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		companyDAO.deleteObject(company);
	}

	@Override
	public List<Company> findAllCompany(int startIndex, int endIndex, String propertyName, boolean ascending)
			throws Exception {
		// TODO Auto-generated method stub
		return (List<Company>) companyDAO.findObjectByFields(clz, null, startIndex, endIndex, propertyName, ascending);
	}

	@Override
	public int getCompanyTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return companyDAO.getObjectTotalCount(clz, Company.PROP_ID);
	}

	@Override
	public boolean updateCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		return companyDAO.updateCompany(company);
	}

	public ICompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Override
	public Company getCompanyById(int id) throws Exception {
		// TODO Auto-generated method stub
		return (Company) this.companyDAO.getObject(clz, id);
	}

}
