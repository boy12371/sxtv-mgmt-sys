package com.vms.db.dao;


import java.util.List;

import com.vms.db.bean.Company;
import com.vms.db.dao.iface.ICompanyDAO;



public class CompanyDAO extends com.vms.db.dao.BaseRootDAO  implements ICompanyDAO{

	private Class clz = com.vms.db.bean.Company.class;

	@Override
	public void deleteCompany(int id) throws Exception {
		// TODO Auto-generated method stub
		
		this.deleteObject(this.loadObject(clz, id));
		
	}

	@Override
	public List<Company> findAllCompanies(int startIndex, int endIndex) throws Exception {
		// TODO Auto-generated method stub
		return (List<Company>)this.findObjectByFields(clz, null, startIndex, endIndex, null, false);
	}

}