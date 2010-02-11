package com.vms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IVediotapeService;

public class VediotapeService implements IVediotapeService {

	private IVediotapeDAO vediotapeDAO;

	private Class clz = com.vms.db.bean.Vediotape.class;

	@Override
	public void createVediotapes(List<Vediotape> vedios) throws Exception {
		// TODO Auto-generated method stub
		vediotapeDAO.saveObjects(vedios);
	}

	// //
	public List<Vediotape> findVediotapeByProperty(String propertyName, Object value, int startIndex, int endIndex,
			boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		vediotapeDAO.findObjectByField(clz, propertyName, value, startIndex, endIndex, asceding);
		return null;
	}

	@Override
	public Vediotape getVediotapeByName(String vedioName) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> propertiesValues = new HashMap<String, Object>();
		propertiesValues.put(Vediotape.PROP_VEDIO_NAME, vedioName);
		return (Vediotape) this.vediotapeDAO.getUniqueResultByProperty(clz, propertiesValues);

	}

	@Override
	public List<Vediotape> findAllVideotapesForAudit(String propertyName, int startIndex, int endIndex, boolean asceding)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] values = {new Integer(2),new Integer(3), new Integer(4),new Integer(5)};
		return vediotapeDAO.findAllVideosInScope(Vediotape.PROP_STATUS+".id", values, propertyName, startIndex, endIndex, asceding);
		
	}

	@Override
	public List<Vediotape> findVideotapeByStatus(int status, String propertyName, int startIndex,
			int endIndex, boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Vediotape.PROP_STATUS, status);
		return (List<Vediotape>) vediotapeDAO
				.findObjectByFields(clz, map, startIndex, endIndex, propertyName, asceding);

	}
	@Override
	public int getTotalCountForAllVideotapesForAudit() throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getTotalCountForAllVideotapesForAudit();
		}

	@Override
	public int getTotalCountForVideosByStatus(int status) throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getObjectTotalCountByFields(clz, Vediotape.PROP_STATUS+".id", status);
	}

	public IVediotapeDAO getVediotapeDAO() {
		return vediotapeDAO;
	}

	public void setVediotapeDAO(IVediotapeDAO vediotapeDAO) {
		this.vediotapeDAO = vediotapeDAO;
	}

	
}
