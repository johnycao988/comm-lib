package com.cly.security;

 
import java.util.Properties; 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse; 

public interface ClientSecurityFilter {	

public boolean	authenticateUser(ServletRequest request, ServletResponse response);

public boolean	authenticateUser(String userId, String authCode);

public boolean authUserGroups(ServletRequest request, ServletResponse response,String[] grpNames);

public boolean authUserGroups(String userId, String authCode,String[] grpNames);

public String getLoginUrl(ServletRequest request, ServletResponse response);

public void initProperties(Properties p);

}
