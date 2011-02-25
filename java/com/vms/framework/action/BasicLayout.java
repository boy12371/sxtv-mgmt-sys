package com.vms.framework.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.vms.action.LogonAction;
import com.vms.common.BaseAction;
import com.vms.common.SessionUserInfo;
import com.vms.db.bean.Company;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.framework.tabview.TabElementBean;
import com.vms.framework.tabview.TabViewManager;
import com.vms.service.iface.ICompanyService;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.ISubjectService;
import com.vms.service.iface.ITopicService;
import com.vms.service.iface.IUserService;

public class BasicLayout extends BaseAction{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static Logger logger = Logger.getLogger(LogonAction.class);
	
	private IUserService userService;
	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;
	private IStatusService statusService;
	
	private SessionUserInfo userInfo;
	
	public SessionUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(SessionUserInfo userInfo) {
		this.userInfo = userInfo;		
	}

	//private List<TabElementBean> tabs;
	
	public String doLogon() throws Exception{
		if(super.getUserInfo() != null){
//			TabViewManager tabManager = TabViewManager.getInstance(this.session);
//			tabs = tabManager.getTabs();
			return SUCCESS;	
		}
		if(userInfo==null || userInfo.getUsername().length()==0 || userInfo.getPassword().length()==0){
			this.addActionError("用户名或密码错误");
			return INPUT;
		}else{
			userInfo = userService.authenticate(userInfo.getUsername(), userInfo.getPassword());
			if(null == userInfo){ 
				this.addActionError("用户名或密码错误");
				return INPUT;
				//throw new BaseException("", "验证失败，用户名或密码错误。")
			}else{
				super.setUserInfo(userInfo);
				TabViewManager tabManager = TabViewManager.getInstance(this.session);
				List<TabElementBean> tabs = tabManager.getTabs();
				this.getSession().setAttribute("NAV_TABS", tabs);
				return SUCCESS;	
			}			
		}
	}
	public List<Company> getComList() throws Exception {
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true,
				true);
	}

	public List<Topic> getTopList() throws Exception {
		return topicService.findAllTopics(-1, -1, Topic.PROP_ID, true, true);
	}

	public List<Subject> getSubList() throws Exception {
		return subjectService.findAllSubjects(-1, -1, Subject.PROP_ID, true,
				true);
	}

	public List<Status> getStatusList() throws Exception {
		return statusService.findAllStatus(-1, -1, Status.PROP_ID, true);
	}
	

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public IStatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(IStatusService statusService) {
		this.statusService = statusService;
	}
}
