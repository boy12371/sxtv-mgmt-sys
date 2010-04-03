package com.vms.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IdentifyFilter implements Filter {

	private List<String> excluedURLs;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.excluedURLs = null;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0); 

		String requestURI = request.getRequestURI();
		boolean mustAuth = true;
		if (excluedURLs != null && !excluedURLs.isEmpty()) {
			for (String url : excluedURLs) {
				if (requestURI.indexOf(url) != -1) {
					mustAuth = false;
					break;
				}
			}
		}

		if (mustAuth) {
			HttpSession session = request.getSession(false);
			if(session ==null || session.getAttribute("SessionUserInfo")==null){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else{
				chain.doFilter(request, response);
			}		
		}else{
			chain.doFilter(request, response);
		}

		

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String params = config.getInitParameter("excluedURLs");
		if (params != null && !"".equals(params)) {
			String[] urls = params.split(",");
			this.excluedURLs = Arrays.asList(urls);
		}

	}

	public List<String> getExcluedURLs() {
		return excluedURLs;
	}

	public void setExcluedURLs(List<String> excluedURLs) {
		this.excluedURLs = excluedURLs;
	}

}
