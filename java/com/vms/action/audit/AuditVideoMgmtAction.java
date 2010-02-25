package com.vms.action.audit;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IAudienceScoreService;
import com.vms.service.iface.IVedioscoreService;
import com.vms.service.iface.IVediotapeService;

public class AuditVideoMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AuditVideoMgmtAction.class);
	private IVediotapeService videoService;
	private IAudienceScoreService audienceScoreService;
	private IVedioscoreService vedioscoreService;
	private int filter = 0;
	private JSONDataTable table;
	private String videoID;
	private VedioTapeVO vv;
	private int operation;

	public String toAuditVideos() throws Exception {
		return SUCCESS;
	}

	public String filterVideos() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> videosList = new ArrayList();
			if (filter != 0) {
				videosList = videoService.findVideotapeByStatus(filter, table.getSort(), table.getStartIndex(), table
						.getStartIndex()
						+ table.getRowsPerPage(), table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
				JSONDataTableUtils.setupJSONDataTable(videosList, table, videoService
						.getTotalCountForVideosByStatus(filter));
			} else {
				videosList = videoService.findAllVideotapesForAudit(table.getSort(), table.getStartIndex(), table
						.getStartIndex()
						+ table.getRowsPerPage(), table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
				JSONDataTableUtils.setupJSONDataTable(videosList, table, videoService
						.getTotalCountForAllVideotapesForAudit());
			
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return SUCCESS;

	}

//	public String toAuditingVideo() throws Exception {
//		try {
//			List<AudienceExamineVO> ae = audienceScoreService.getAudienceScoreOfTape(videoID, -1, -1, "", true);
//			vv = videoService.getVideotapeById(videoID, ae);
//			// vedioscoreService.getUserExaminedVedioesByVideoId(videoID,
//			// startIndex, endIndex, propertyName, ascending);
//			return SUCCESS;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return INPUT;
//		}
//	}

	public String getVideoScores() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		List<VedioScoreVO> data = vedioscoreService.findUserExamineScoreByVideoId(videoID, table.getStartIndex(),
				table.getStartIndex() + table.getRowsPerPage(), table.getSort(), table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
		JSONDataTableUtils.setupJSONDataTable(data, table, vedioscoreService
				.getTotalCountUserExamineScoreByVideoId(videoID));
		return SUCCESS;
	}

	
	public String videoAuditOperation() throws Exception{
		SessionUserInfo user = new SessionUserInfo(1); //this.getUserInfo();
		boolean flag=false;
		try {
			flag = videoService.auditingVideo(vv.getVedioID(), user, operation);	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);			
		}
		
		if(!flag){
			this.addActionError("审核失败.");
			return INPUT;
		}
		this.addActionMessage("审核成功.");
		return SUCCESS;
		
	}
	
	public IVediotapeService getVideoService() {

		return videoService;
	}

	public void setVideoService(IVediotapeService videoService) {
		this.videoService = videoService;
	}

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}

	public IAudienceScoreService getAudienceScoreService() {
		return audienceScoreService;
	}

	public void setAudienceScoreService(IAudienceScoreService audienceScoreService) {
		this.audienceScoreService = audienceScoreService;
	}

	

	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public VedioTapeVO getVv() {
		return vv;
	}

	public void setVv(VedioTapeVO vv) {
		this.vv = vv;
	}

	public IVedioscoreService getVedioscoreService() {
		return vedioscoreService;
	}

	public void setVedioscoreService(IVedioscoreService vedioscoreService) {
		this.vedioscoreService = vedioscoreService;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

}
