package com.cly.security;

public class AuthPermission {

	private String name;
	
	private boolean isPermitted;
	
	public AuthPermission(String permissionName, boolean isPermitted){
		
		this.name=permissionName;
		
		this.isPermitted=isPermitted;
	}
	
	public String getName() {
		return name;
	}

 
	public boolean isPermitted() {
		return isPermitted;
	}
	
}
