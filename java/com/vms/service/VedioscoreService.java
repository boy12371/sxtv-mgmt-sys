package com.vms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vms.beans.VedioScoreVO;
import com.vms.beans.VedioTapeVO;
import com.vms.common.CommonVariable;
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
	public List<VedioTapeVO> getAllUnExaminedVedioes(int startIndex, int endIndex, String orderName, boolean dir) throws Exception {
		//get all examiners
		List<User> users = findAllExaminer();
		List<String> names = new ArrayList<String>();
		for(User user:users){
			names.add(user.getEmployee().getName());
		}
		
		Status status = new Status(1);
		List<Vediotape> tapes = vediotapeDAO.findVedioesByStatus(status, startIndex, endIndex, orderName, dir);
		List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
		for (Vediotape tape : tapes) {
			List<User> examinedUsers = findExaminedUsersOfTape(tape.getId());
			List<String> examinedNames = new ArrayList<String>();
			for(User user:examinedUsers){
				examinedNames.add(user.getEmployee().getName());
			}
			List<String> temp = new ArrayList<String>(names);
			temp.removeAll(examinedNames);
			
			VedioTapeVO tapeVO = new VedioTapeVO(tape);
			tapeVO.setExaminedEmployees(examinedNames);
			tapeVO.setUnexaminedEmployees(temp);
			
			tapeVOs.add(tapeVO);
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

		Map<String, Float> weights = getWeights();
		Float sum = score.getPerformScore() * weights.get("performScore") + score.getInnovateScore()
				* weights.get("innovateScore") + score.getStoryScore() * weights.get("storyScore")
				+ score.getTechScore() * weights.get("techScore");
		score.setScore(sum);

		vedioscoreDAO.saveOrUpdateObject(score);
	}
	
	public void updateTapeExamineStatus(VedioScoreVO scoreVO) throws Exception{
//		int examinerNum = vedioscoreDAO.findAllExaminer().size();
//		List<User> examinedUser = findExaminedUsersOfTape(scoreVO.getVedioID());
//		if(examinerNum <= examinedUser.size()){
			Vediotape tape = (Vediotape)vediotapeDAO.loadObject(Vediotape.class, scoreVO.getVedioID());
			tape.setStatus(new Status(CommonVariable.VIDEO_STATUS_AUDITING));
			vediotapeDAO.saveOrUpdateObject(tape);
//		}
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
		Vediotape video = (Vediotape) vediotapeDAO.getObject(Vediotape.class, videoID);
		List<Vedioscore> list = vedioscoreDAO.findObjectByField(Vedioscore.class, Vedioscore.PROP_VEDIO, video, startIndex, endIndex, propertyName, ascending);
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
		if(null == list.get(0).getExaminer().getEmployee()){
			vedioscoreDAO.refreshObject(list.get(0));
		}
		VedioScoreVO vs = new VedioScoreVO(list.get(0));
		return vs;
	}
	
	public List<User> findExaminedUsersOfTape(String videoID) throws Exception{
		List<User> users = new ArrayList<User>();
		List<Vedioscore> list = vedioscoreDAO.findObjectByField(Vedioscore.class, Vedioscore.PROP_VEDIO, new Vediotape(videoID), -1, -1, Vedioscore.PROP_DATE_EXAMINE, true);
		if(null != list || 0 != list.size()){
			for(Vedioscore score:list){
				users.add(score.getExaminer());
			}
		}
		return users;
	}
	
	public List<User> findAllExaminer() throws Exception{
		return vedioscoreDAO.findAllExaminer();
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
