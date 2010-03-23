package com.vms.action.sysconfig;

import org.apache.log4j.Logger;

import com.vms.common.BaseAction;
import com.vms.db.bean.Scorelevel;
import com.vms.db.bean.Scoreweight;
import com.vms.service.iface.IScorelevelService;
import com.vms.service.iface.IScoreweightService;

public class LeveWeightMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -42781449889822392L;
	private static Logger logger = Logger.getLogger(LeveWeightMgmtAction.class);
	private IScorelevelService scorelevelService;
	private IScoreweightService scoreweightService;

	private Scoreweight scoreweight;
	private Scorelevel scorelevel;

	public String modifyWeight() throws Exception {
		try {
			scoreweightService.updateWeight(scoreweight);
			this.addActionMessage("修改成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("修改失败");
		return INPUT;
	}

	public String modifyLevel() throws Exception {
		try {
			scorelevelService.updateLevel(scorelevel);
			this.addActionMessage("修改成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("修改失败");
		return INPUT;
	}

	public String addScoreLevel()throws Exception{
		try {
			scorelevelService.createLevel(scorelevel);
			this.addActionMessage("添加成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("添加失败");
		return INPUT;
	}
	public IScorelevelService getScorelevelService() {
		return scorelevelService;
	}

	public void setScorelevelService(IScorelevelService scorelevelService) {
		this.scorelevelService = scorelevelService;
	}

	public IScoreweightService getScoreweightService() {
		return scoreweightService;
	}

	public void setScoreweightService(IScoreweightService scoreweightService) {
		this.scoreweightService = scoreweightService;
	}

	public Scoreweight getScoreweight() {
		return scoreweight;
	}

	public void setScoreweight(Scoreweight scoreweight) {
		this.scoreweight = scoreweight;
	}

	public Scorelevel getScorelevel() {
		return scorelevel;
	}

	public void setScorelevel(Scorelevel scorelevel) {
		this.scorelevel = scorelevel;
	}

}
