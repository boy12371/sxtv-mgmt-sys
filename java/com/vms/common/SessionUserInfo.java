package com.vms.common;

import java.util.HashMap;
import java.util.Map;

public class SessionUserInfo {
	
	private int userId;	
	
	private String username;
	
	private String password;
	
	private Map<String, Integer> permissions = new HashMap<String, Integer>();
	
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
	public void setPermissions(Map<String, Integer> permissions) {
		this.permissions = permissions;
	}
	public Map<String, Integer> getPermissions() {
		return permissions;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
