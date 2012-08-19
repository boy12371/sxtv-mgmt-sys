package com.vms.common;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vms.db.bean.Resources;
import com.vms.service.iface.IResourceService;

public class ResourceServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getServletContext().removeAttribute("urlAuthorities");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext servletContext= arg0.getServletContext();
		IResourceService resourceService  = (IResourceService) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("resourceService");
		try {
			List<Resources> rList = resourceService.findAllResources();
			List<String> res = new ArrayList<String>();
			for (Resources resources : rList) {
				res.add(resources.getUrl());
			}
			servletContext.setAttribute("urlAuthorities", res);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
