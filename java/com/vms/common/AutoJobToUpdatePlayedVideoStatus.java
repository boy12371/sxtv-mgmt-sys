package com.vms.common;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vms.db.bean.Playorder;
import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IVediotapeDAO;
import com.vms.service.iface.IPlayorderService;
import com.vms.service.iface.IVediotapeService;

public class AutoJobToUpdatePlayedVideoStatus{
	
	private static Logger logger = Logger.getLogger(AutoJobToUpdatePlayedVideoStatus.class);
	private IVediotapeService vediotapeService;
	private IPlayorderService playorderService;
	
	
	public void doUpdateStatus() throws Exception{
		Calendar cal = Calendar.getInstance();
		List<Playorder> list = playorderService.findPlayedVideosByDate(cal.getTime());
		if(list != null && !list.isEmpty()){
			for (Playorder playorder : list) {
				Vediotape v = playorder.getVedioID();
				int formerStatus = v.getStatus().getId();
				v.setStatus(new Status(8));
				if(vediotapeService.updateVideo(v)){
					logger.info("SYSTEM AUTO JOB===== "+(cal.getTime().toLocaleString()+" system update status from "+formerStatus+"(preparing play) to 8(played) of video: "+ v.getVedioName()+"/id:"+v.getId()));
				}		
			}	
		}else{
			logger.info("SYSTEM AUTO JOB===== 0 vedios founds and 0 updated on "+cal.getTime().toLocaleString());
		}
	}

	public IPlayorderService getPlayorderService() {
		return playorderService;
	}


	public void setPlayorderService(IPlayorderService playorderService) {
		this.playorderService = playorderService;
	}

	public IVediotapeService getVediotapeService() {
		return vediotapeService;
	}

	public void setVediotapeService(IVediotapeService vediotapeService) {
		this.vediotapeService = vediotapeService;
	}

}
