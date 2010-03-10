package com.vms.common;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vms.db.bean.Playorder;
import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IPlayorderService;

public class AutoJobToUpdatePlayedVideoStatus{
	private IVediotapeDAO vediotapeDAO;
	private IPlayorderService playorderService;
	
	
	public void doUpdateStatus() throws Exception{
		Calendar cal = Calendar.getInstance();
		List<Playorder> list = playorderService.findPlayedVideosByDate(cal.getTime());
		if(list != null && !list.isEmpty()){
			for (Playorder playorder : list) {
				Vediotape v = playorder.getVedioID();
				v.setStatus(new Status(8));
				vediotapeDAO.saveOrUpdateObject(v);
			}	
		}
	}


	public IVediotapeDAO getVediotapeDAO() {
		return vediotapeDAO;
	}


	public void setVediotapeDAO(IVediotapeDAO vediotapeDAO) {
		this.vediotapeDAO = vediotapeDAO;
	}


	public IPlayorderService getPlayorderService() {
		return playorderService;
	}


	public void setPlayorderService(IPlayorderService playorderService) {
		this.playorderService = playorderService;
	}

}
