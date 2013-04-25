package com.sx.tv.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.sx.tv.entites.Resource;
import com.sx.tv.entites.Role;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static final Logger logger = Logger.getLogger(MySecurityMetadataSource.class);

	private static HashMap<String, Collection<ConfigAttribute>> resourceMap = null;

	private PathMatcher urlMatcher = new AntPathMatcher();

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes(Object) - start"); //$NON-NLS-1$  
		}
		String url = ((FilterInvocation) object).getRequestUrl();
		if (null == resourceMap || this.resourceMap.isEmpty()) {
			this.loadAllRolesResources();
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.match(resURL, url)) {
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);
				if (logger.isDebugEnabled()) {
					logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$  
				}
				return returnCollection;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$  
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@PostConstruct
	public void loadAllRolesResources() {
		if(null == resourceMap){
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		}
		List<Role> roles = entityManager.createQuery("SELECT o FROM Role o", Role.class)
				.getResultList();
		for (Role role : roles) {
			Set<Resource> res = role.getResources();
			for (Resource r : res) {
				Collection<ConfigAttribute> atts = (ArrayList) this.resourceMap.get(r.getResouceURL());
				if (null != atts) {
					atts.add(new SecurityConfig(role.getName()));
				} else {
					atts = new ArrayList<ConfigAttribute>();
					atts.add(new SecurityConfig(role.getName()));
					this.resourceMap.put(r.getResouceURL(), atts);
				}
				//this.resourceMap.put(r.getResouceURL(), atts);
			}
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public HashMap<String, Collection<ConfigAttribute>> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(HashMap<String, Collection<ConfigAttribute>> resourceMap) {
		this.resourceMap = resourceMap;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
