package com.cly.security;

 
import java.io.IOException;
import java.util.Properties; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

public interface ClientSecurityFilter {	

public void init(Properties p);

public boolean	authenticateUser(HttpServletRequest request, HttpServletResponse response,  boolean forwardUserLoginPage)  throws IOException;

public boolean	authenticateUser(String userId, String authCode);

public boolean accessPermmission(HttpServletRequest request, HttpServletResponse response,String[] permissionNames);

public boolean accessPermmission(String userId, String authCode,String[] permissionNames); 

public PermissionAuthResult getAccessPermmission(HttpServletRequest request, HttpServletResponse response,String[] permissionNames) throws IOException;

public PermissionAuthResult getAccessPermmission(String userId, String authCode,String[] permissionNames) throws IOException; 

public boolean logout(String userId, String authCode);  

public boolean	logout(HttpServletRequest request, HttpServletResponse response);

public ClientSecurityFilter addExcludeUri(String uri); 

}
