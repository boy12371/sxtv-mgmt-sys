package com.vms.action.sysconfig;

import com.vms.common.BaseAction;
import com.vms.db.bean.Subject;
import com.vms.service.iface.ISubjectService;

public class SubjectMgmtAction extends BaseAction {

	private ISubjectService subjectService;

	private Subject subject;

	public String toAddSubject() {

		return this.SUCCESS;
	}

	public String doAddSubject() throws Exception {

		subjectService.createSubject(subject);
		return this.SUCCESS;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
