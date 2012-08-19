package com.vms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Company;
import com.vms.db.dao.iface.ICompanyDAO;
import com.vms.service.iface.ICompanyService;

public class CompanyService implements ICompanyService {

	private ICompanyDAO companyDAO;
	private Class clz = Company.class;

	@Override
	public void createCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		company.setStatus(new Integer(1));
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
	public List<Company> findAllCompany(int startIndex, int endIndex,
			String propertyName, boolean ascending, boolean activeOnly)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> propertiesValues = null;
		if (activeOnly) {
			propertiesValues = new HashMap<String, Object>();
			propertiesValues.put(Company.PROP_STATUS, new Integer(1));
		}
		return (List<Company>) companyDAO
				.findObjectByFields(clz, propertiesValues, startIndex,
						endIndex, propertyName, ascending);
	}

	@Override
	public int getCompanyTotalCount(boolean activeOnly) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> propertiesValues = null;
		if (activeOnly) {
			propertiesValues = new HashMap<String, Object>();
			propertiesValues.put(Company.PROP_STATUS, new Integer(1));
		}
		return companyDAO.getTotalCount_findObjectByFields(clz,
				propertiesValues);

	}

	@Override
	public boolean updateCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		Company com = (Company) companyDAO.getObject(clz, company.getId());
		com.setComments(company.getComments());
		com.setCompanyName(company.getCompanyName());
		com.setContactPerson(company.getContactPerson());
		com.setPhone(company.getPhone());
		com.setRegistrationNo(company.getRegistrationNo());		
		companyDAO.saveOrUpdateObject(com);
		return true;
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

	@Override
	public void disableEnableCompany(int id, boolean enable) throws Exception {
		// TODO Auto-generated method stub
		Company com = (Company) this.companyDAO.getObject(clz, id);
		com.setStatus(enable ? 1 : 0);
		companyDAO.saveOrUpdateObject(com);
	}

}
