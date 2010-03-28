package com.vms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = -3233938101251331689L;

	private static Logger logger = Logger.getLogger(BaseAction.class);
	
	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;
	
	//protected CommonVariable commonVar = new CommonVariable();

	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public SessionUserInfo getUserInfo(){
		return (SessionUserInfo)session.getAttribute("SessionUserInfo");
	}
	
	protected void setUserInfo(SessionUserInfo userInfo){
		session.setAttribute("SessionUserInfo", userInfo);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}
