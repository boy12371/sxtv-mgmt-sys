package com.vms.action.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
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
	private IVediotapeService vedioService;

	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;
	private IStatusService statusService;
	private JSONDataTable table;
	private Vediotape video;
	private Date startDate;
	private Date endDate;
	private String query;

	public String autoCompleteForVideoName() throws Exception {
		List<String> names = vedioService.findVideoNamesForAutoComplete(query);
		List<VideoNameJSON> nameList =new ArrayList<VideoNameJSON>();
		table=new JSONDataTable();
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
		}
		if(video!=null){
			return SUCCESS;	
		}else{
			this.addActionError("影带未找到");
			return INPUT;
		}
		
	}


	public String searchVideos()throws Exception{
		
		
		return this.SUCCESS;
	}

	
	public List<Company> getComList() throws Exception{
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true);		
	}


	public List<Topic> getTopList() throws Exception{
		return topicService.findAllTopics(-1,-1, Topic.PROP_ID, true);
	}
	
	public List<Subject> getSubList() throws Exception{
		return subjectService.findAllSubjects(-1, -1, Subject.PROP_ID, true);
	}
	
	public List<Status> getStatusList() throws Exception{
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

	public class VideoNameJSON{
		public String vname;
		
		public VideoNameJSON(String vname){
			this.vname=vname;
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
}
