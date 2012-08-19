package com.vms.action.sysconfig;

import org.apache.log4j.Logger;

import com.vms.common.BaseAction;
import com.vms.db.bean.Subject;
import com.vms.service.iface.ISubjectService;

public class SubjectMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20252784219812121L;
	private static Logger logger = Logger.getLogger(SubjectMgmtAction.class);
	private ISubjectService subjectService;
	private Subject subject;
	private boolean enableOperator;

	public String doAddSubject() throws Exception {
		try {
			subjectService.createSubject(subject);
			this.addActionMessage("添加成功");
			return this.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("添加失败");
		return INPUT;
		
	}
	public String doDisableEnableSubject() throws Exception{
		try {
			this.subjectService.disableEnable(subject.getId(), enableOperator);
			this.addActionMessage("栏目已禁用");
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
		}
		this.addActionError("操作失败");
		return INPUT;
	}
	
	public String modifySubject() throws Exception{
		try {
			this.subjectService.updateSubject(subject);
			this.addActionMessage("修改成功");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		this.addActionError("修改失败");
		return INPUT;
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
	public boolean isEnableOperator() {
		return enableOperator;
	}
	public void setEnableOperator(boolean enableOperator) {
		this.enableOperator = enableOperator;
	}
}
