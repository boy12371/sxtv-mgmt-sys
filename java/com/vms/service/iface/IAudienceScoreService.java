package com.vms.service.iface;

import java.util.List;

import com.vms.beans.AudienceExamineVO;
import com.vms.db.bean.Audience;

public interface IAudienceScoreService {
	public List<Audience> getAllAudience(String propertyName, boolean ascending) throws Exception;
	public List<AudienceExamineVO> getAudienceScoreOfTape(String vedioID, int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception;
	public int getCountAudienceOfTape(String vedioID) throws Exception;
	public void updateAudienceScore(List<AudienceExamineVO> aes) throws Exception;
}
