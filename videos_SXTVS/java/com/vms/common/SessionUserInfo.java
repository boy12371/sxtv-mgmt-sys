package com.vms.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionUserInfo {

	private int userId;

	private String username;

	private String password;
	
	private List<Integer> roles;
	
	private String strRoles;

//	private Map<String, Integer> permissions = new HashMap<String, Integer>();

	private List<String> authoritedResource;
	
	public SessionUserInfo() {

	}

	public SessionUserInfo(int userId) {
		this.userId = userId;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

//	public void setPermissions(Map<String, Integer> permissions) {
//		this.permissions = permissions;
//	}
//
//	public Map<String, Integer> getPermissions() {
//		return permissions;
//	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getAuthoritedResource() {
		return authoritedResource;
	}

	public void setAuthoritedResource(List<String> authoritedResource) {
		this.authoritedResource = authoritedResource;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public void setStrRoles(String strRoles) {
		this.strRoles = strRoles;
	}

	public String getStrRoles() {
		return strRoles;
	}	
}
