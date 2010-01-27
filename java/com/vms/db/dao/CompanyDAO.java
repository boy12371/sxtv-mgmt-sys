package com.vms.db.dao;

import java.util.List;

import com.vms.db.bean.Company;
import com.vms.db.dao.iface.ICompanyDAO;

public class CompanyDAO extends com.vms.db.dao.BaseRootDAO implements ICompanyDAO {

	private Class clz = com.vms.db.bean.Company.class;

	@Override
	public void deleteCompany(int id) throws Exception {
		// TODO Auto-generated method stub

		this.deleteObject(this.loadObject(clz, id));

	}

	@Override
	public List<Company> findAllCompanies(int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub
		return (List<Company>) this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
	}

	@Override
	public boolean updateCompany(Company company) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Company com set com.companyName=?, com.registrationNo=?, com.phone=?, com.contactPerson=?, com.comments=? where com.id=?";
		int result = this.getHibernateTemplate().bulkUpdate(
				hql,
				new Object[] { company.getCompanyName(), company.getRegistrationNo(), company.getPhone(),
						company.getContactPerson(), company.getComments(), company.getId() });

		return result != 0;

	}

}