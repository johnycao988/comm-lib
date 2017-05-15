package com.cly.security;

import java.util.Properties;

public interface UserInfoService {
	
	public void initProperties(Properties p) throws SecurityServerException;
	
	public void setPasswordEncryptService(PasswordEncrypt pwdService);
	
	public UserInfo login(String userId, String userPwd) throws SecurityServerException;
	
}
