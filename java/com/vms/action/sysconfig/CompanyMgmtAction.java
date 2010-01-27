package com.vms.action.sysconfig;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Company;
import com.vms.service.iface.ICompanyService;

public class CompanyMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CompanyMgmtAction.class);
	private ICompanyService companyService;

	private Company company;

	private List<Company> comList;

	private JSONDataTable table;

	
	public String toCompanies(){
		return SUCCESS;
	}
	public String getCompanies() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			comList = companyService.findAllCompany(table.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir().equals("asc"));
			JSONDataTableUtils.setupJSONDataTable(comList,table,companyService.getCompanyTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String toAddCompany() {

		return this.SUCCESS;
	}

	public String doAddCompany() throws Exception {
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
	public List<Company> getComList() {
		return comList;
	}
	public void setComList(List<Company> comList) {
		this.comList = comList;
	}
	public JSONDataTable getTable() {
		return table;
	}
	public void setTable(JSONDataTable table) {
		this.table = table;
	}

}
