package com.cly.security;


public interface UserInfo {
	
	public String getUserId();
	public String getUserName(); 
	public String getAuthCode(); 
	public String[] getUserGroups();
	
}
