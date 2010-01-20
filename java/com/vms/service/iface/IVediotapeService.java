package com.vms.service.iface;

import java.util.List;
import java.util.Map;

import com.vms.db.bean.Vediotape;

public interface IVediotapeService {

	/***
	 * find vedio tapes by company 
	 * @param CompanyID
	 * @return
	 */
	List findVediotapeByFields(Map<String, Object> fieldsNameValue) throws Exception;
	
	void createVediotapes(List<Vediotape> vedioes) throws Exception;
	
	
}
