package com.vms.action.arrangement;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vms.beans.JSONDataTable;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.db.bean.Playorder;
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
	private JSONDataTable table;
	private int status;
	private int month;

	private String orderString;

	public String toArrangePlayOrder() throws Exception {
		videoList = vedioService.findVideotapeByStatus(status,
				Vediotape.PROP_DATE_COMING, -1, -1, true);
		return this.SUCCESS;
	}

	public String createPlayorder() throws Exception {
		//orderService.savePlayorder(orderString);
		return this.SUCCESS;
	}

	public String getOrderListForMonth() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		Date date = cal.getTime();

		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Playorder> pList = orderService.findPlayorderByMonth(date);
			JSONDataTableUtils.setupJSONDataTable(pList, table, pList.size());
		} catch (Exception e) {
			// TODO: handle exception

		}

		return SUCCESS;
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
