package com.vms.action.sysconfig;

import java.util.List;

import com.vms.common.BaseAction;
import com.vms.db.bean.Company;
import com.vms.service.iface.ICompanyService;

public class CompanyMgmtAction extends BaseAction {
	
	
	private ICompanyService companyService;
	
	private Company company;
	
	private List<Company> comList;
	
	
	
	public String toCompanies() throws Exception{
		comList = companyService.findAllCompany(0, -1);
		return this.SUCCESS;
	}
	public String toAddCompany(){
		
		return this.SUCCESS;
	}
	
	
	
	
	public String doAddCompany() throws Exception{
		companyService.createCompany(company);
		return this.SUCCESS;
	}

	
	

	public ICompanyService getCompanyService() {
		return companyService;
	}


	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}
