package com.vms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Company;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.VediotapeDAO;
import com.vms.db.dao.iface.IBaseRootDAO;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IVediotapeService;

public class VediotapeService implements IVediotapeService {

	private IVediotapeDAO vediotapeDAO;

	@Override
	public List findVediotapeByFields(Map<String, Object> fieldsNameValue) throws Exception {
		// TODO Auto-generated method stub	
		return vediotapeDAO.findVediosByProperties(fieldsNameValue, 0, 10,
				Vediotape.PROP_COMPANY_ID, true);

	}

	
	@Override
	public void createVediotapes(List<Vediotape> vedios) throws Exception {
		// TODO Auto-generated method stub
		this.vediotapeDAO.saveVedios(vedios);

	}
	
	
	
	public IVediotapeDAO getVediotapeDAO() {
		return vediotapeDAO;
	}

	public void setVediotapeDAO(IVediotapeDAO vediotapeDAO) {
		this.vediotapeDAO = vediotapeDAO;
	}



}
