package com.vms.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Auditing;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IAuditingDAO;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IVediotapeService;

public class VediotapeService implements IVediotapeService {

	private IVediotapeDAO vediotapeDAO;
	private IAuditingDAO auditingDAO;

	private Class clz = com.vms.db.bean.Vediotape.class;

	@Override
	public void createVediotapes(List<Vediotape> vedios) throws Exception {
		// TODO Auto-generated method stub
		vediotapeDAO.saveObjects(vedios);
	}

	@Override
	public List<Vediotape> findVediotapeByProperty(String propertyName, Object value, int startIndex, int endIndex,
			String orderPropertyName, boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		return (List<Vediotape>) vediotapeDAO.findObjectByField(clz, propertyName, value, startIndex, endIndex,
				orderPropertyName, asceding);

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
		Object[] values = { new Integer(2), new Integer(3), new Integer(4), new Integer(5) };
		return vediotapeDAO.findAllVideosInScope(Vediotape.PROP_STATUS + ".id", values, propertyName, startIndex,
				endIndex, asceding);

	}

	@SuppressWarnings("unchecked")
	@Override
	/*
	 * status: tape status
	 * propertyName: the column data return form database sorting by 
	 */
	public List<Vediotape> findVideotapeByStatus(int status, String propertyName, int startIndex, int endIndex,
			boolean asceding) throws Exception {
		// TODO Auto-generated method stub

		return (List<Vediotape>) vediotapeDAO.findObjectByField(clz, Vediotape.PROP_STATUS, new Status(status),
				startIndex, endIndex, propertyName, asceding);

	}

	@Override
	public int getTotalCountForAllVideotapesForAudit() throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getTotalCountForAllVideotapesForAudit();
	}

	@Override
	public int getTotalCountForVideosByStatus(int status) throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getTotalCount_findObjectByField(clz, Vediotape.PROP_STATUS, new Status(status));
	}

	@Override
	public VedioTapeVO getVideotapeById(String videoID, List<AudienceExamineVO> audienceVote) throws Exception {
		// TODO Auto-generated method stub
		Vediotape vt = (Vediotape) vediotapeDAO.getObject(clz, videoID);
		VedioTapeVO vv = new VedioTapeVO(vt);
		int size = audienceVote.size();
		int watch = 0;
		for (int i = 0; i < size; i++) {
			AudienceExamineVO aev = audienceVote.get(i);
			if (aev.getResult().equals(AudienceExamineVO.yes)) {
				watch++;
			}

		}
		Map<String, Integer> watching = new HashMap<String, Integer>();
		watching.put(AudienceExamineVO.yes, new Integer(watch));
		watching.put(AudienceExamineVO.no, new Integer(size - watch));
		vv.setWatching(watching);
		return vv;
	}

	public IVediotapeDAO getVediotapeDAO() {
		return vediotapeDAO;
	}

	public void setVediotapeDAO(IVediotapeDAO vediotapeDAO) {
		this.vediotapeDAO = vediotapeDAO;
	}

	public VedioTapeVO getTapeByID(String ID) throws Exception {
		Vediotape tape = (Vediotape) vediotapeDAO.getObject(Vediotape.class, ID);
		return new VedioTapeVO(tape);
	}

	@Override
	public boolean auditingVideo(String vedioId, SessionUserInfo user, int operation) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Vediotape tape set tape.status.id=? where tape.id=?";
		boolean success = vediotapeDAO.updateVideotape(hql, new Object[] { operation, vedioId });
		Serializable id = null;
		if (success) {
			Auditing audit = new Auditing();
			audit.setVedioID(new Vediotape(vedioId));
			audit.setAuditor(new User(user.getUserId()));
			audit.setResult(new Status(operation));
			audit.setAuditDate(new Date());
			id = auditingDAO.saveObject(audit);
		} else {
			return false;
		}

		return id != null;
	}

	@Override
	public List<String> findVideoNamesForAutoComplete(String videoName) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Vediotape v where v.vedioName like ?";
		Map<String, Object[]> valuesTypes = new HashMap<String, Object[]>();
		valuesTypes.put("values", new Object[] { "%" + videoName + "%" });
		valuesTypes.put("types", new Type[] { Hibernate.STRING });
		List<Vediotape> list = (List<Vediotape>) vediotapeDAO.findVideos(hql, valuesTypes, -1, -1);
		List<String> names = new ArrayList<String>();
		if (list != null && !list.isEmpty()) {
			for (Vediotape vediotape : list) {
				names.add(vediotape.getVedioName());
			}

		}
		return names;

	}

	

	@Override
	public boolean updateVideoRatingMarket(String videoID, float market, float rate) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Vediotape v set v.marketShare=?, set v.audienceRating=? where v.id=?";
		boolean result = vediotapeDAO.updateVideotape(hql, new Object[] { market, rate, videoID });
		if (result) {

		}
		return false;
	}


	public IAuditingDAO getAuditingDAO() {
		return auditingDAO;
	}

	public void setAuditingDAO(IAuditingDAO auditingDAO) {
		this.auditingDAO = auditingDAO;
	}
}
