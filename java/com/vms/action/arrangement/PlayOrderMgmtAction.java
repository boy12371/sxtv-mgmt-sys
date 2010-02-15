package com.vms.action.arrangement;

import java.util.List;

import com.vms.common.BaseAction;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.IPlayorderService;
import com.vms.service.iface.IVediotapeService;

public class PlayOrderMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private IVediotapeService vedioService;
	private IPlayorderService orderService;
	private List<Vediotape> videoList;
	private int status;
	
	private String orderString;	
	
	public String toArrangePlayOrder()throws Exception{		
		videoList = vedioService.findVideotapeByStatus(status, Vediotape.PROP_DATE_COMING, -1, -1, true);		
		return this.SUCCESS;
	}

	public String createPlayorder()throws Exception{
		orderService.savePlayorder(orderString);
		return this.SUCCESS;
	}
	
	public IVediotapeService getVedioService() {
		return vedioService;
	}


	public void setVedioService(IVediotapeService vedioService) {
		this.vedioService = vedioService;
	}




	public IPlayorderService getOrderService() {
		return orderService;
	}


	public void setOrderService(IPlayorderService orderService) {
		this.orderService = orderService;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getOrderString() {
		return orderString;
	}


	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

	public List<Vediotape> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Vediotape> videoList) {
		this.videoList = videoList;
	}
	
	
	

}
