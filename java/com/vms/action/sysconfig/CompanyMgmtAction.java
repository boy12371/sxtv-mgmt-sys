package com.vms.action.sysconfig;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

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
	private JSONDataTable table;

	public String toCompanies() {
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getCompanies() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Company> comList = companyService.findAllCompany(table
					.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir()
					.equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(comList, table,
					companyService.getCompanyTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return this.SUCCESS;
	}

	public String toAddCompany() {
		return this.SUCCESS;
	}

	public String doAddCompany() throws Exception {
		try {
			companyService.createCompany(company);
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.addActionError("添加失败");
			return this.INPUT;
		}
		this.addActionMessage("添加成功");
		return this.SUCCESS;
	}

	public String toUpdateCompany() throws Exception {
		try {
			company = companyService.getCompanyById(company.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.INPUT;
		}
		return this.SUCCESS;
	}

	public String doUpdateCompany() {
		boolean success = false;
		try {
			success = companyService.updateCompany(company);
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.addActionError("添加失败");
			return this.INPUT;
		}
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

	

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}

}
