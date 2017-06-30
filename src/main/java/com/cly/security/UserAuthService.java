package com.cly.security;

import java.util.Properties;

public interface UserAuthService {
	
	public void initProperties(Properties p) throws SecurityAuthException;
	
	public UserInfo login(String userId, String userPwd) throws SecurityAuthException;
	
	public boolean logout(String userId, String autCode) throws SecurityAuthException;
	
	public boolean authenticate(String userId, String autCode) throws SecurityAuthException;
	
	public PermissionAuthResult authPermissions(String userId, String authCode, String[] authPermissionNames) throws SecurityAuthException;
	
		
}
