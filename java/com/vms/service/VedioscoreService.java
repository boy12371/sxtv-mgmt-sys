package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Scoreweight;
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

	public VedioTapeVO getTapeByID(String ID) throws Exception {
		Vediotape tape = (Vediotape) vedioscoreDAO.getObject(Vediotape.class, ID);
		return new VedioTapeVO(tape);
	}

	@Override
	public List<VedioTapeVO> getAllUnExaminedVedioes(int startIndex, int endIndex) throws Exception {
		Status status = new Status(1);
		List<Vediotape> tapes = vediotapeDAO.findVedioesByStatus(status, startIndex, endIndex);
		List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
		for (Vediotape tape : tapes) {
			tapeVOs.add(new VedioTapeVO(tape));
		}
		return tapeVOs;
	}

	public int getVedioCountByStatus(Status status) throws Exception {
		return vediotapeDAO.getTotalCount_findObjectByField(Vediotape.class, Vediotape.PROP_STATUS, status);
	}

	public int getCountOfUserExaminedVedio(User user) throws Exception {
		return vedioscoreDAO.getTotalCount_findObjectByField(VedioscoreDAO.clz, Vedioscore.PROP_EXAMINER, user);
	}

	public void saveVedioScore(VedioScoreVO scoreVO) throws Exception {
		Vedioscore score = scoreVO.toVedioscore();
		// get examiner object for userID
		List<User> users = vedioscoreDAO.findObjectByField(User.class, User.PROP_USER_NAME, scoreVO.getExaminer(), -1,
				-1, "", true);
		score.setExaminer(users.get(0));

		Map<String, Float> weights = getWeights();
		Float sum = score.getPerformScore() * weights.get("performScore") + score.getInnovateScore()
				* weights.get("innovateScore") + score.getStoryScore() * weights.get("storyScore")
				+ score.getTechScore() * weights.get("techScore");
		score.setScore(sum);

		vedioscoreDAO.saveOrUpdateObject(score);
	}

	private Map<String, Float> getWeights() throws Exception {
		Map<String, Float> map = new HashMap<String, Float>();
		List<Scoreweight> weights = vedioscoreDAO.findAll(Scoreweight.class);
		for (Scoreweight w : weights) {
			map.put(w.getId(), w.getWieght());
		}
		return map;
	}

	@Override
	public List<VedioScoreVO> findUserExamineScoreByVideoId(String videoID, int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception {
		// TODO Auto-generated method stub
		List<VedioScoreVO> voList = new ArrayList<VedioScoreVO>();
		List<Vedioscore> list = vedioscoreDAO.findObjectByField(Vedioscore.class, Vedioscore.PROP_VEDIO, new Vediotape(
				videoID), startIndex, endIndex, propertyName, ascending);
		if (list != null && !list.isEmpty()) {
			for (Vedioscore vedioscore : list) {
				voList.add(new VedioScoreVO(vedioscore));
			}
		}
		return voList;
	}
	
	public VedioScoreVO getTapeScoreByIdAndUser(String videoID, int userID) throws Exception {
		// TODO Auto-generated method stub
		List<VedioScoreVO> voList = new ArrayList<VedioScoreVO>();
		Map<String, Object> propertiesValues = new HashMap<String, Object>();
		propertiesValues.put(Vedioscore.PROP_VEDIO, new Vediotape(videoID));
		propertiesValues.put(Vedioscore.PROP_EXAMINER, new User(userID));
		List<Vedioscore> list = vedioscoreDAO.findObjectByFields(Vedioscore.class, propertiesValues, -1, -1, Vedioscore.PROP_ID, true);
		if (list == null || 0 == list.size()) {
			return null;
		}
		VedioScoreVO vs = new VedioScoreVO(list.get(0));
		return vs;
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

	@Override
	public int getTotalCountUserExamineScoreByVideoId(String videoID) throws Exception {
		// TODO Auto-generated method stub
		return vediotapeDAO.getTotalCount_findObjectByField(Vedioscore.class, Vedioscore.PROP_VEDIO, new Vediotape(videoID));
	}

}
