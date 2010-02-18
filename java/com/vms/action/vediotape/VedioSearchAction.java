package com.vms.action.vediotape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vms.action.audit.AuditVideoMgmtAction;
import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IAudienceScoreService;
import com.vms.service.iface.IVedioscoreService;
import com.vms.service.iface.IVediotapeService;

public class VedioSearchAction extends BaseAction {

	private static Logger logger = Logger.getLogger(AuditVideoMgmtAction.class);
	private IVediotapeService videoService;
	private int filter = 0;
	private JSONDataTable table;
	private String query;

	public String filterVideos() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> videosList = new ArrayList();
			videosList = videoService.findVideotapeByStatus(8, table.getSort(), table.getStartIndex(), table
					.getStartIndex()
					+ table.getRowsPerPage(), table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(videosList, table, videoService
					.getTotalCountForVideosByStatus(filter));

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return SUCCESS;

	}
}
