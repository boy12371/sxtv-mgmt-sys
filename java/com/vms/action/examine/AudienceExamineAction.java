package com.vms.action.examine;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.vms.beans.AudienceExamineVO;
import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Audience;
import com.vms.db.bean.Company;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.User;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IAudienceScoreService;
import com.vms.service.iface.IVediotapeService;

public class AudienceExamineAction extends BaseAction {

	private static final long serialVersionUID = 5465976325539246570L;

	private static Logger logger = Logger.getLogger(AudienceExamineAction.class);
	
	private IAudienceScoreService audienceExamineService;
	
	private IVediotapeService tapeService;

	private JSONDataTable audienceExamineTable;
	
	private List<Audience> audience;
	
	private VedioTapeVO tape;
	
	private String newResult;

	public String toAudienceExamine() throws Exception {
		try{
			tape = tapeService.getTapeByID(tape.getVedioID());
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
					audienceExamineTable.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(AEs, audienceExamineTable, audienceExamineService.getCountAudienceOfTape(tape.getVedioID()));
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return SUCCESS;
	}
	
	public String doAudienceExamine() throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(newResult);
		List<AudienceExamineVO> aes = new ArrayList<AudienceExamineVO>();
		if(jsonArray.isArray() && !jsonArray.isEmpty()){
			int size = jsonArray.size();
			for (int i = 0; i < size; i++) {
				JSONObject obj =jsonArray.getJSONObject(i);
				AudienceExamineVO aev = new AudienceExamineVO();
				aev.setAudience(obj.getString("audience"));
				String tapeID = obj.getString("tapeID");
				aev.setTapeID(tapeID);
				if(null == tape){
					tape = new VedioTapeVO();
					tape.setVedioID(tapeID);
				}
				
				aev.setResult(obj.getString("result"));
				int mark = obj.getInt("marked");
				if(2 == mark){
					aev.setId(new Integer(obj.getInt("id")));
				}
				aes.add(aev);
			}
		}
		audienceExamineService.updateAudienceScore(aes);
		//back to original page
		toAudienceExamine();
		return SUCCESS;
	}

	public void setAudienceExamineTable(JSONDataTable audienceExamineTable) {
		this.audienceExamineTable = audienceExamineTable;
	}

	public JSONDataTable getAudienceExamineTable() {
		return audienceExamineTable;
	}

	public void setAudienceExamineService(IAudienceScoreService audienceExamineService) {
		this.audienceExamineService = audienceExamineService;
	}

	public IAudienceScoreService getAudienceExamineService() {
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
	
	public IVediotapeService getTapeService() {
		return tapeService;
	}

	public void setTapeService(IVediotapeService tapeService) {
		this.tapeService = tapeService;
	}

	public void setNewResult(String newResult) {
		this.newResult = newResult;
	}

	public String getNewResult() {
		return newResult;
	}
}
