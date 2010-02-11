package com.vms.action.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IAudienceScoreService;
import com.vms.service.iface.IVediotapeService;

public class AuditVideoMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AuditVideoMgmtAction.class);
	private IVediotapeService videoService;
	private IAudienceScoreService audienceScoreService;
	private int filter = 0;
	private JSONDataTable table;
	private String videoID;

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
						+ table.getRowsPerPage(), table.getDir().equals("asc"));
				JSONDataTableUtils.setupJSONDataTable(videosList, table, videoService
						.getTotalCountForVideosByStatus(filter));
			} else {
				videosList = videoService.findAllVideotapesForAudit(table.getSort(), table.getStartIndex(), table
						.getStartIndex()
						+ table.getRowsPerPage(), table.getDir().equals("asc"));
				JSONDataTableUtils.setupJSONDataTable(videosList, table, videoService
						.getTotalCountForAllVideotapesForAudit());
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return SUCCESS;

	}

	
	public String toAuditingVideo() throws Exception{
		
		List<AudienceExamineVO> ae =audienceScoreService.getAudienceScoreOfTape(videoID, -1, -1, "", true);
		
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

}
