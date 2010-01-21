package com.vms.action.sysconfig;

import com.vms.common.BaseAction;
import com.vms.db.bean.Status;
import com.vms.service.iface.IStatusService;

public class StatusMgmtAction extends BaseAction {

	private IStatusService statusService;
	private Status status;

	public String toAddStatus() {
		return SUCCESS;
	}

	public String doAddStatus() throws Exception {

		statusService.createStatus(status);
		return SUCCESS;

	}

	public IStatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(IStatusService statusService) {
		this.statusService = statusService;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
