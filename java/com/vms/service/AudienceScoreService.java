package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.beans.AudienceExamineVO;
import com.vms.db.bean.Audience;
import com.vms.db.bean.Audiencescore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IAudienceScoreDAO;
import com.vms.service.iface.IAudienceScoreService;

public class AudienceScoreService implements IAudienceScoreService{
	
	private IAudienceScoreDAO audienceScoreDAO;
	
	public IAudienceScoreDAO getAudienceScoreDAO() {
		return audienceScoreDAO;
	}

	public void setAudienceScoreDAO(IAudienceScoreDAO audienceScoreDAO) {
		this.audienceScoreDAO = audienceScoreDAO;
	}

	public List<Audience> getAllAudience(String propertyName, boolean ascending) throws Exception{
		return audienceScoreDAO.findObjectByFields(Audience.class, null, -1, -1, propertyName, ascending);
	}
	
	public List<AudienceExamineVO> getAudienceScoreOfTape(String vedioID, int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception{
		Map<String, Object> conditions = new HashMap<String, Object>();
		List<AudienceExamineVO> AEVOs = new ArrayList<AudienceExamineVO>();
		
		Vediotape tape = new Vediotape();
		tape.setId(vedioID);		
		conditions.put(Audiencescore.PROP_VEDIO_I_D, tape);
		
		List<Audiencescore> AEs= audienceScoreDAO.findObjectByFields(Audiencescore.class,conditions,startIndex,endIndex,propertyName,false);
		for(Audiencescore AE:AEs){
			AEVOs.add(new AudienceExamineVO(AE));
		}
		return AEVOs;
	}
	
	public int getCountAudienceOfTape(String vedioID) throws Exception{
		Vediotape tape = new Vediotape();
		tape.setId(vedioID);
		return audienceScoreDAO.getTotalCount_findObjectByField(Audiencescore.class, Audiencescore.PROP_VEDIO_I_D, tape);
	}
	
	public void updateAudienceScore(List<AudienceExamineVO> aes) throws Exception{
		Map<String, Audience> ausMap = getAllAudience();
		for(AudienceExamineVO ae:aes){
			Audiencescore as = ae.toAudiencescore();
			as.setAudienceID(ausMap.get(as.getAudienceID().getName()));
			audienceScoreDAO.saveOrUpdateObject(as);
		}
	}
	
	private Map<String, Audience> getAllAudience() throws Exception{
		Map<String, Audience> ausMap = new HashMap<String, Audience>();
		List<Audience> ausList = audienceScoreDAO.findAll(Audience.class);
		for(Audience a:ausList){
			ausMap.put(a.getName(), a);
		}
		return ausMap;
	}
}
