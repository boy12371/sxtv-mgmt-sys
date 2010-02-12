package com.vms.service.iface;

import java.util.List;
import java.util.Map;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Vediotape;


public interface IVediotapeService {	
	
	
	List<Vediotape> findVideotapeByStatus(int status,String propertyName,
			int startIndex, int endIndex, boolean asceding)throws Exception;
	public List<Vediotape> findVediotapeByProperty(String propertyName, Object value, int startIndex, int endIndex,String orderPropertyName,
			boolean asceding) throws Exception;
	int getTotalCountForVideosByStatus(int status)throws Exception;
	
	
	
	
	
	/***
	 * 查询所有待审和剧目（待审，修改，重审,通过）
	 * @param propertyName
	 * @param startIndex
	 * @param endIndex
	 * @param asceding
	 * @return
	 * @throws Exception
	 */
	List<Vediotape> findAllVideotapesForAudit(String propertyName,int startIndex, int endIndex, boolean asceding)throws Exception;
	
	/***
	 * 查询所有待审，修改，重审,通过的剧目总数
	 * @return
	 * @throws Exception
	 */
	int getTotalCountForAllVideotapesForAudit() throws Exception;
	void createVediotapes(List<Vediotape> vedioes) throws Exception;
	
	
	Vediotape getVediotapeByName(String vedioName)throws Exception;
	
	VedioTapeVO getTapeByID(String ID) throws Exception;
	VedioTapeVO getVideotapeById(String videoID, List<AudienceExamineVO> audienceVote) throws Exception;
	
	boolean auditingVideo(String vedioId, SessionUserInfo user, int operation)throws Exception;
}
