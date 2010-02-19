package com.vms.action.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IVediotapeService;

public class VedioSearchMgmt extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IVediotapeService vedioService;
	private JSONDataTable table;
	private Vediotape video;
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

	
	public String getChartFor(){
		
	
		return this.SUCCESS;
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