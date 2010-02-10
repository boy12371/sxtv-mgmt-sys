package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.beans.AudienceExamineVO;
import com.vms.db.bean.Audience;
import com.vms.db.bean.Audiencescore;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.VedioscoreDAO;
import com.vms.db.dao.iface.IAudienceExamineDAO;
import com.vms.service.iface.IAudienceExamineService;

public class AudienceExamineService implements IAudienceExamineService{
	
	private IAudienceExamineDAO audienceExamineDAO;
	
	public List<Audience> getAllAudience(String propertyName, boolean ascending) throws Exception{
		return audienceExamineDAO.findObjectByFields(Audience.class, null, -1, -1, propertyName, ascending);
	}
	
	public List<AudienceExamineVO> getAudienceScoreOfTape(String vedioID, int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception{
		Map<String, Object> conditions = new HashMap<String, Object>();
		List<AudienceExamineVO> AEVOs = new ArrayList<AudienceExamineVO>();
		
		Vediotape tape = new Vediotape();
		tape.setId(vedioID);		
		conditions.put(Audiencescore.PROP_VEDIO_I_D, tape);
		
		List<Audiencescore> AEs= audienceExamineDAO.findObjectByFields(Audiencescore.class,conditions,startIndex,endIndex,propertyName,false);
		for(Audiencescore AE:AEs){
			AEVOs.add(new AudienceExamineVO(AE));
		}
		return AEVOs;
	}
	
	public int getCountAudienceOfTape(String vedioID) throws Exception{
		Vediotape tape = new Vediotape();
		tape.setId(vedioID);
		return audienceExamineDAO.getObjectTotalCountByFields(Audiencescore.class, Audiencescore.PROP_VEDIO_I_D, tape);
	}

	public void setAudienceExamineDAO(IAudienceExamineDAO audienceExamineDAO) {
		this.audienceExamineDAO = audienceExamineDAO;
	}

	public IAudienceExamineDAO getAudienceExamineDAO() {
		return audienceExamineDAO;
	}
}
