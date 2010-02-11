package com.vms.service.iface;

import java.util.List;

import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Status;

public interface IVedioscoreService {
	
	public List<VedioTapeVO> getAllUnExaminedVedioes(int startIndex, int endIndex) throws Exception;
	
	public List<VedioScoreVO> getUserExaminedVedioes(String username,int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception;
	
	/***
	 * 获取所有对此剧目的评分
	 * @param videoID
	 * @param startIndex
	 * @param endIndex
	 * @param propertyName
	 * @param ascending
	 * @return
	 * @throws Exception
	 */
	public List<VedioScoreVO> findUserExamineScoreByVideoId(String videoID,int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception;
	/**
	 * 获取所有对此剧目的评分的总数量
	 * @param videoID
	 * @return
	 * @throws Exception
	 */
	public int getTotalCountUserExamineScoreByVideoId(String videoID) throws Exception;
	public int getVedioCountByStatus(Status status) throws Exception;
	
	public int getCountOfUserExaminedVedio(String username) throws Exception;
	
	public VedioTapeVO getTapeByID(String ID) throws Exception;
	
	public void saveVedioScore(VedioScoreVO scoreVO) throws Exception;
}
