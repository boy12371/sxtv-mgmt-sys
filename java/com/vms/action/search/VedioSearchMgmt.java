package com.vms.action.search;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.SearchCondition;
import com.vms.db.bean.Company;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.ICompanyService;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.ISubjectService;
import com.vms.service.iface.ITopicService;
import com.vms.service.iface.IVediotapeService;

public class VedioSearchMgmt extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(VedioSearchMgmt.class);
	private IVediotapeService vedioService;
	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;
	private IStatusService statusService;
	private JSONDataTable table;
	private Vediotape video;
	private SearchCondition sc;
	private String query;
	private boolean isSequenceOrder;
	private String vid;
	private String jsonData;

	public String toGenericSeaching() throws Exception {
		return this.SUCCESS;
	}

	public String autoCompleteForVideoName() throws Exception {
		List<String> names = vedioService
				.findVideoNamesForAutoComplete(java.net.URLDecoder.decode(
						query, "UTF-8"));
		List<VideoNameJSON> nameList = new ArrayList<VideoNameJSON>();
		table = new JSONDataTable();
		if (names != null && !names.isEmpty()) {
			int size = names.size();
			for (int i = 0; i < size; i++) {
				nameList.add(new VideoNameJSON(names.get(i)));
			}
		}
		table.setRecords(nameList);
		return SUCCESS;
	}

	public String searchVideoByName() throws Exception {
		try {
			video = vedioService.getVediotapeByName(query);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		if (video != null) {
			return SUCCESS;
		} else {
			this.addActionError("影带未找到");
			return INPUT;
		}

	}

	public String toVideoDetail() throws Exception {
		List<Vediotape> list = vedioService.findVediotapeByProperty(
				Vediotape.PROP_ID, vid, -1, -1, "", false);
		if (list != null && !list.isEmpty()) {
			this.video = list.get(0);
			return SUCCESS;
		} else {
			this.addActionError("影带未找到");
			return INPUT;
		}

	}

	public String searchVideos() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = this.vedioService.findVidesByConditions(
					sc, table.getSort(), table.getStartIndex(), table
							.getStartIndex()
							+ table.getRowsPerPage(), table.getDir().equals(
							JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(dataList, table, vedioService
					.getTotalCountForVidesByConditions(sc));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);

		}
		return this.SUCCESS;
	}

	public String toPrintVideosReport() throws UnsupportedEncodingException {
		query = java.net.URLDecoder.decode(query, "UTF-8");
		return SUCCESS;
	}

	public String doPrintVideosReport() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = this.vedioService
							.findVidesByConditions(sc, table.getSort(), table
									.getStartIndex(), table.getStartIndex()
									+ table.getRowsPerPage(), table.getDir().equals(
									JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(dataList, table, dataList.size());	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return this.SUCCESS;
	}

	public String toPrintVideosSequenceOrderReport() throws UnsupportedEncodingException {
		query = java.net.URLDecoder.decode(query, "UTF-8");
		return SUCCESS;
	}

	public String doPrintVideosSequenceOrderReport() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {			
			List<Vediotape> dataList =this.vedioService.findVideosByRateOrder(sc, table
							.getSort(), table.getStartIndex(), table
							.getStartIndex()
							+ table.getRowsPerPage(), table.getDir().equals(
							JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(dataList, table, dataList.size());	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return this.SUCCESS;
	}
	public String toVideoSequence() {
		return SUCCESS;
	}

	public String doSearchAndSequenceVideos() {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = this.vedioService.findVideosByRateOrder(
					sc, table.getSort(), table.getStartIndex(), table
							.getStartIndex()
							+ table.getRowsPerPage(), table.getDir().equals(
							JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(dataList, table, dataList
					.size());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);

		}
		return this.SUCCESS;
	}

	public List<Company> getComList() throws Exception {
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true,
				false);
	}

	public List<Topic> getTopList() throws Exception {
		return topicService.findAllTopics(-1, -1, Topic.PROP_ID, true);
	}

	public List<Subject> getSubList() throws Exception {
		return subjectService.findAllSubjects(-1, -1, Subject.PROP_ID, true);
	}

	public List<Status> getStatusList() throws Exception {
		return statusService.findAllStatus(-1, -1, Status.PROP_ID, true);
	}

	public IVediotapeService getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVediotapeService vedioService) {
		this.vedioService = vedioService;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public class VideoNameJSON {
		public String vname;

		public VideoNameJSON(String vname) {
			this.vname = vname;
		}

		public String getVname() {
			return vname;
		}

		public void setVname(String vname) {
			this.vname = vname;
		}

	}

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}

	public Vediotape getVideo() {
		return video;
	}

	public void setVideo(Vediotape video) {
		this.video = video;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public IStatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(IStatusService statusService) {
		this.statusService = statusService;
	}

	public SearchCondition getSc() {
		return sc;
	}

	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public boolean isSequenceOrder() {
		return isSequenceOrder;
	}

	public void setSequenceOrder(boolean isSequenceOrder) {
		this.isSequenceOrder = isSequenceOrder;
	}

}
