package com.vms.service;

import java.io.Serializable;
import java.net.Socket;
import java.text.ParseException;
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
import com.vms.common.SearchCondition;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Auditing;
import com.vms.db.bean.Company;
import com.vms.db.bean.Scorelevel;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IAuditingDAO;
import com.vms.db.dao.iface.IScorelevelDAO;
import com.vms.db.dao.iface.IVedioscoreDAO;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IScorelevelService;
import com.vms.service.iface.IVediotapeService;

public class VediotapeService implements IVediotapeService {

	private IVediotapeDAO vediotapeDAO;
	private IAuditingDAO auditingDAO;
	private IVedioscoreDAO vedioscoreDAO;
	private IScorelevelDAO scorelevelDAO;
	private Class clz = com.vms.db.bean.Vediotape.class;

	@Override
	public void createVediotapes(List<Vediotape> vedios) throws Exception {
		// TODO Auto-generated method stub
		vediotapeDAO.saveObjects(vedios);
	}

	@Override
	public List<Vediotape> findVediotapeByProperty(String propertyName,
			Object value, int startIndex, int endIndex,
			String orderPropertyName, boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		return (List<Vediotape>) vediotapeDAO.findObjectByField(clz,
				propertyName, value, startIndex, endIndex, orderPropertyName,
				asceding);

	}

	@Override
	public Vediotape getVediotapeByName(String vedioName) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> propertiesValues = new HashMap<String, Object>();
		propertiesValues.put(Vediotape.PROP_VEDIO_NAME, vedioName);
		return (Vediotape) this.vediotapeDAO.getUniqueResultByProperty(clz,
				propertiesValues);

	}

	@Override
	public List<Vediotape> findAllVideotapesForAudit(String propertyName,
			int startIndex, int endIndex, boolean asceding) throws Exception {
		// TODO Auto-generated method stub
		Status[] values = { new Status(2), new Status(3), new Status(5), new Status(7) };
		return vediotapeDAO.findAllVideosInScope(Vediotape.PROP_STATUS,
				values, propertyName, startIndex, endIndex, asceding);

	}

	@SuppressWarnings("unchecked")
	@Override
	/*
	 * status: tape status propertyName: the column data return form database
	 * sorting by
	 */
	public List<Vediotape> findVideotapeByStatus(int status,
			String propertyName, int startIndex, int endIndex, boolean asceding)
			throws Exception {
		// TODO Auto-generated method stub

		return (List<Vediotape>) vediotapeDAO.findObjectByField(clz,
				Vediotape.PROP_STATUS, new Status(status), startIndex,
				endIndex, propertyName, asceding);

	}

	@Override
	public int getTotalCountForAllVideotapesForAudit() throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getTotalCountForAllVideotapesForAudit();
	}

	@Override
	public int getTotalCountForVideosByStatus(int status) throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getTotalCount_findObjectByField(clz,
				Vediotape.PROP_STATUS, new Status(status));
	}

	@Override
	public VedioTapeVO getVideotapeById(String videoID,
			List<AudienceExamineVO> audienceVote) throws Exception {
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
		Vediotape tape = (Vediotape) vediotapeDAO
				.getObject(Vediotape.class, ID);
		return new VedioTapeVO(tape);
	}

	@Override
	public boolean auditingVideo(String vedioId, SessionUserInfo user,
			int operation) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Vediotape tape set tape.status.id=? where tape.id=?";
		boolean success = vediotapeDAO.updateVideotape(hql, new Object[] {
				operation, vedioId });
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
	public List<String> findVideoNamesForAutoComplete(String videoName)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Vediotape v where v.vedioName like ?";
		Map<String, Object[]> valuesTypes = new HashMap<String, Object[]>();
		valuesTypes.put("values", new Object[] { "%" + videoName + "%" });
		valuesTypes.put("types", new Type[] { Hibernate.STRING });
		List<Vediotape> list = (List<Vediotape>) vediotapeDAO.findVideos(hql,
				valuesTypes, -1, -1);
		List<String> names = new ArrayList<String>();
		if (list != null && !list.isEmpty()) {
			for (Vediotape vediotape : list) {
				names.add(vediotape.getVedioName());
			}

		}
		return names;

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateVideoRatingMarket(String videoID, float market,
			float rate) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Vediotape v set v.marketShare=?, v.audienceRating=?, v.status.id=? where v.id=?";
		boolean result = vediotapeDAO.updateVideotape(hql, new Object[] {
				market, rate, 8, videoID });
		float vScore = 0;
		List<Scorelevel> levels = this.findAllLevels();
		for (Scorelevel scorelevel : levels) {
			if(rate > scorelevel.getStart() && rate <= scorelevel.getEnd()){
				vScore = (scorelevel.getStart()+scorelevel.getEnd()) / 2;
				break;
			}
		}		
		
		if (result) {
			List<Vedioscore> scoreList = vedioscoreDAO.findObjectByField(
					Vedioscore.class, Vedioscore.PROP_VEDIO, new Vediotape(
							videoID), -1, -1, "", false);
			for (Vedioscore vs : scoreList) {
				float acc = Math.abs(vs.getScore() - vScore);
				vs.setAccuracy(acc);
				vedioscoreDAO.saveOrUpdateObject(vs);
			}
		}
		return true;
	}

	private List<Scorelevel> findAllLevels() throws Exception{
		List<Scorelevel> levels = this.scorelevelDAO.findAll(Scorelevel.class);
		return levels;
	}
	@Override
	public boolean updateVideoInfo(Vediotape video) throws Exception {
		// TODO Auto-generated method stub
		String hql = "update Vediotape v set v.vedioName=?, v.companyID=?, v.subject=?, v.topic=?, v.marketShare=?, v.audienceRating=?, v.comments=?  where v.id=?";
		Object[] args = { video.getVedioName(),
				new Company(video.getCompanyID().getId()),
				new Subject(video.getSubject().getId()),
				new Topic(video.getTopic().getId()), video.getMarketShare(),
				video.getAudienceRating(), video.getComments(), video.getId() };
		return vediotapeDAO.updateVideotape(hql, args);
	}

	@Override
	public List<Vediotape> findVidesByConditions(SearchCondition condition,
			String propertyName, int startIndex, int endIndex, boolean asceding)
			throws Exception {

		return this.vediotapeDAO.findVideosByFieldsNamePartiallyDateInScope(
				this.convert(condition), startIndex, endIndex, propertyName,
				asceding);

	}

	public int getTotalCountByConditions() throws Exception {
		return 0;
	}

	@Override
	public int getTotalCountForVidesByConditions(SearchCondition condition)
			throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO
				.getTotalCountForVideosByFieldsNamePartiallyDateInScope(this
						.convert(condition));
	}

	private Map<String, Object> convert(SearchCondition condition)
			throws Exception {
		Map<String, Object> fieldsValues = new HashMap<String, Object>();

		if (condition.getId() != null && !"".equals(condition.getId())) {
			fieldsValues.put(Vediotape.PROP_ID, condition.getId());
		}
		if (condition.getName() != null && !"".equals(condition.getName())) {
			fieldsValues.put(Vediotape.PROP_VEDIO_NAME, condition.getName());
		}
		if (condition.getCompany() != null && condition.getCompany().getId()!=0) {
			fieldsValues.put(Vediotape.PROP_COMPANY_ID, condition.getCompany());
		}
		if (condition.getTopic() != null && condition.getTopic().getId()!=0) {
			fieldsValues.put(Vediotape.PROP_TOPIC, condition.getTopic());
		}
		if (condition.getSubject() != null && condition.getSubject().getId()!=0) {
			fieldsValues.put(Vediotape.PROP_SUBJECT, condition.getSubject());
		}
		if (condition.getStatus() != null && condition.getStatus().getId()!=0) {
			fieldsValues.put(Vediotape.PROP_STATUS, condition.getStatus());
		}
		if (condition.getStartingDate() != null) {
			fieldsValues.put("startDate", condition.getStartingDate());
		}
		if (condition.getEndingDate() != null) {
			fieldsValues.put("endDate", condition.getEndingDate());
		}
		return fieldsValues;
	}

	public IAuditingDAO getAuditingDAO() {
		return auditingDAO;
	}

	public void setAuditingDAO(IAuditingDAO auditingDAO) {
		this.auditingDAO = auditingDAO;
	}

	public IVedioscoreDAO getVedioscoreDAO() {
		return vedioscoreDAO;
	}

	public void setVedioscoreDAO(IVedioscoreDAO vedioscoreDAO) {
		this.vedioscoreDAO = vedioscoreDAO;
	}

	@Override
	public boolean updateVideo(Vediotape video) throws Exception {
		// TODO Auto-generated method stub
		this.vediotapeDAO.saveOrUpdateObject(video);
		return true;
	}

	public IScorelevelDAO getScorelevelDAO() {
		return scorelevelDAO;
	}

	public void setScorelevelDAO(IScorelevelDAO scorelevelDAO) {
		this.scorelevelDAO = scorelevelDAO;
	}



}
