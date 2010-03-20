package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Company;


public interface ICompanyService {

	
	void deleteCompany(int id)throws Exception;
	void deleteCompany(Company company)throws Exception;
	
	/***
	 * 查询所有公司列表
	 * @param startIndex 
	 * @param endIndex
	 * @param propertyName 排序列名
	 * @param ascending  true=升序 false=降序
	 * @param activeOnly 只查寻状态为1的
	 * @return
	 * @throws Exception
	 */
	List<Company> findAllCompany(int startIndex, int endIndex,
			String propertyName, boolean ascending,boolean activeOnly) throws Exception;

	/**
	 * 创建公司
	 * @param company 
	 * @throws Exception
	 */
	void createCompany(Company company)throws Exception;
	
	/***
	 * 得到公司总数量
	 * @return
	 * @throws Exception
	 */
	int getCompanyTotalCount(boolean activeOnly) throws Exception;
	
	/**
	 * 更新公司信息 不级联更新
	 * @param company
	 * @return
	 * @throws Exception
	 */
	boolean updateCompany(Company company) throws Exception;
	
	Company getCompanyById(int id)throws Exception;
	
	void disableEnableCompany(int id, boolean enable)throws Exception;
}
