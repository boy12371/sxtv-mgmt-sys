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

	private Class clz = com.vms.db.bean.Vediotape.class;

	@Override
	public void createVediotapes(List<Vediotape> vedios) throws Exception {
		// TODO Auto-generated method stub
		this.vediotapeDAO.saveVedios(vedios);

	}

	@Override
	public List<Vediotape> findVediotapeByProperty(Class clz,
			String propertyName, Object value, int startIndex, int endIndex,
			boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		vediotapeDAO.findObjectByField(clz, propertyName, value, startIndex,
				endIndex, asceding);
		return null;
	}

	public IVediotapeDAO getVediotapeDAO() {
		return vediotapeDAO;
	}

	public void setVediotapeDAO(IVediotapeDAO vediotapeDAO) {
		this.vediotapeDAO = vediotapeDAO;
	}

}
