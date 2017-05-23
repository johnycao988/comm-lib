package com.cly.security;

import java.util.Properties;

public interface UserInfoService {
	
	public void initProperties(Properties p) throws SecurityAuthException;
	
	public UserInfo login(String userId, String userPwd) throws SecurityAuthException;
	
}
