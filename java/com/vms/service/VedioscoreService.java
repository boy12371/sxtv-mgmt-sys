package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Status;
import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.VedioscoreDAO;
import com.vms.db.dao.iface.IVedioscoreDAO;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IVedioscoreService;

public class VedioscoreService implements IVedioscoreService {
	
	private IVedioscoreDAO vedioscoreDAO;
	private IVediotapeDAO vediotapeDAO;

	@Override
	public List<VedioScoreVO> getUserExaminedVedioes(String username,int startIndex, int endIndex, String propertyName, boolean ascending) throws Exception {
		Map<String, Object> conditions = new HashMap<String, Object>();
		
		List<User> users = vedioscoreDAO.findObjectByField(com.vms.db.bean.User.class, User.PROP_USER_NAME, username, -1, -1, true);
		conditions.put(Vedioscore.PROP_EXAMINER, users.get(0));
		List<VedioScoreVO> scoreVOs = new ArrayList<VedioScoreVO>();
		List<Vedioscore> scores = vedioscoreDAO.findObjectByFields(VedioscoreDAO.clz,conditions,startIndex,endIndex,Vedioscore.PROP_DATE_EXAMINE,false);
		for(Vedioscore score:scores){
			scoreVOs.add(new VedioScoreVO(score));
		}
		return scoreVOs;
	}

	@Override
	public List<VedioTapeVO> getAllUnExaminedVedioes(int startIndex, int endIndex) throws Exception {
		Status status = new Status(1);
		List<Vediotape> tapes = vediotapeDAO.findVedioesByStatus(status, startIndex, endIndex);
		List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
		for(Vediotape tape:tapes){
			tapeVOs.add(new VedioTapeVO(tape));
		}
		return tapeVOs;
	}
	
	public int getVedioCountByStatus(Status status) throws Exception {
		return vediotapeDAO.getVedioTotalCountByStatus(status);
	}
	
	public int getCountOfUserExaminedVedio(String username) throws Exception {
		List<User> users = vedioscoreDAO.findObjectByField(com.vms.db.bean.User.class, User.PROP_USER_NAME, username, -1, -1, true);
		return vedioscoreDAO.getObjectTotalCountByFields(VedioscoreDAO.clz, Vedioscore.PROP_EXAMINER, users.get(0));
	}

	public void setVedioscoreDAO(IVedioscoreDAO vedioscoreDAO) {
		this.vedioscoreDAO = vedioscoreDAO;
	}

	public IVedioscoreDAO getVedioscoreDAO() {
		return vedioscoreDAO;
	}

	public void setVediotapeDAO(IVediotapeDAO vediotapeDAO) {
		this.vediotapeDAO = vediotapeDAO;
	}

	public IVediotapeDAO getVediotapeDAO() {
		return vediotapeDAO;
	}

	
}
