package com.vms.action.sysconfig;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Audience;
import com.vms.service.iface.IAudicenceService;

public class AudienceMgmtAction extends BaseAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2425616649230788449L;
	private static Logger logger = Logger.getLogger(AudienceMgmtAction.class);
	private IAudicenceService audicenceService;
	private JSONDataTable table;
	private Audience audience;
//	private String operation;
	
	public String toAllAudience(){
		return this.SUCCESS;
	}
	public String getAllAudiences()throws Exception{
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		
		try {
			List<Audience> auds = this.audicenceService.findAudience(table.getStartIndex(), table.getStartIndex()
					+ table.getRowsPerPage(), table.getSort(), table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(auds, table, audicenceService.getTotalCount());
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return SUCCESS;
	}

	public String toAddAudience(){
		return SUCCESS;
	}
	public String addAudience(){
		try {
			this.audicenceService.addAudience(audience);
			this.addActionMessage("添加成功");
			return SUCCESS;	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("添加失败");
		return INPUT;
	}

	public IAudicenceService getAudicenceService() {
		return audicenceService;
	}


	public void setAudicenceService(IAudicenceService audicenceService) {
		this.audicenceService = audicenceService;
	}


	public JSONDataTable getTable() {
		return table;
	}


	public void setTable(JSONDataTable table) {
		this.table = table;
	}


	public Audience getAudience() {
		return audience;
	}


	public void setAudience(Audience audience) {
		this.audience = audience;
	}
	
	
	
}
