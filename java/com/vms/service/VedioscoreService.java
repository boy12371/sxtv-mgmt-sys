package com.vms.service;

import java.util.ArrayList;
import java.util.List;

import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.VedioscoreDAO;
import com.vms.db.dao.VediotapeDAO;
import com.vms.db.dao.iface.IVedioscoreDAO;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IVedioscoreService;

public class VedioscoreService implements IVedioscoreService {
	
	private IVedioscoreDAO vedioscoreDAO;
	private IVediotapeDAO vediotapeDAO;

	@Override
	public List<VedioTapeVO> getAllExaminedVedioes(int startIndex, int endIndex) throws Exception {
		Status status = new Status(2);
		List<Vediotape> tapes = vediotapeDAO.findVedioesByStatus(status, startIndex, endIndex);
		List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
		for(Vediotape tape:tapes){
			tapeVOs.add(new VedioTapeVO(tape));
		}
		return tapeVOs;
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
	
	public int getVedioTotalCountByStatus(Status status) throws Exception {
		return vediotapeDAO.getVedioTotalCountByStatus(status);
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
