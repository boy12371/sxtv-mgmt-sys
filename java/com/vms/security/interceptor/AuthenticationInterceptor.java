package com.vms.security.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vms.common.SessionUserInfo;

public class AuthenticationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation action) throws Exception {
		// TODO Auto-generated method stub
//		ActionContext ac = action.getInvocationContext();
//		SessionUserInfo  user = (SessionUserInfo) ac.getSession().get("SessionUserInfo");
//		HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
//		String requestURI = request.getRequestURI();
//		
//		List<String> authedResource = user.getAuthoritedResource();
//		for (String uri : authedResource) {
//			if(requestURI.indexOf(uri)!= -1){
//				return action.invoke();
//			}
//			
//		}
		return action.invoke();
		//return "403";
	}

}
