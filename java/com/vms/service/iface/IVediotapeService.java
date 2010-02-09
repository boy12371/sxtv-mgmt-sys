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
	List<Vediotape> findVediotapeByProperty(Class clz,String propertyName, Object value,
			int startIndex, int endIndex, boolean asceding) throws Exception;
	
	void createVediotapes(List<Vediotape> vedioes) throws Exception;
	
	
	Vediotape getVediotapeByName(String vedioName)throws Exception;
	
	
}
