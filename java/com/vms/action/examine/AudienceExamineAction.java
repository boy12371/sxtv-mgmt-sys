package com.vms.action.examine;

import java.util.List;

import org.apache.log4j.Logger;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Audience;
import com.vms.service.iface.IAudienceExamineService;

public class AudienceExamineAction extends BaseAction {

	private static final long serialVersionUID = 5465976325539246570L;

	private static Logger logger = Logger.getLogger(AudienceExamineAction.class);
	
	private IAudienceExamineService audienceExamineService;
	
	private JSONDataTable audienceExamineTable;
	
	private List<Audience> audience;
	
	private VedioTapeVO tape;

	public String toAudienceExamine() throws Exception {
		try{
			setAudience(audienceExamineService.getAllAudience("name", true));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String getAudienceExamine() throws Exception {
		audienceExamineTable = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<AudienceExamineVO> AEs = audienceExamineService.getAudienceScoreOfTape(
					tape.getVedioID(), -1, -1, 
					audienceExamineTable.getSort(),
					audienceExamineTable.getDir().equals("asc"));
			JSONDataTableUtils.setupJSONDataTable(AEs, audienceExamineTable, audienceExamineService.getCountAudienceOfTape(tape.getVedioID()));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String doAudienceExamine() {
		return SUCCESS;
	}

	public void setAudienceExamineTable(JSONDataTable audienceExamineTable) {
		this.audienceExamineTable = audienceExamineTable;
	}

	public JSONDataTable getAudienceExamineTable() {
		return audienceExamineTable;
	}

	public void setAudienceExamineService(IAudienceExamineService audienceExamineService) {
		this.audienceExamineService = audienceExamineService;
	}

	public IAudienceExamineService getAudienceExamineService() {
		return audienceExamineService;
	}

	public void setAudience(List<Audience> audience) {
		this.audience = audience;
	}

	public List<Audience> getAudience() {
		return audience;
	}

	public void setTape(VedioTapeVO tape) {
		this.tape = tape;
	}

	public VedioTapeVO getTape() {
		return tape;
	}
	
}
