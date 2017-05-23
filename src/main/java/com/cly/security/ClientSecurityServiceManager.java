package com.cly.security;

import java.util.Properties;
 
import com.cly.security.SecurityAuthException;

public class ClientSecurityServiceManager {
	
	private static Properties securityProperties = null;
	private static ClientSecurityFilter secuFilterService = null;
 
	private ClientSecurityServiceManager() {

	}

	public static void init(Properties prop) {
		securityProperties=prop;  
		secuFilterService = null; 
	}

 

	public static ClientSecurityFilter getClientSecurityFilterService() throws SecurityAuthException {

		if (secuFilterService == null) {
			
			secuFilterService = (ClientSecurityFilter) createServiceInstance("cloud.security.client.filter.service");

			secuFilterService.initProperties(securityProperties); 
		}

		return secuFilterService;

	} 

	private static Object createServiceInstance(String propName) throws SecurityAuthException {

		try {

		 	String className = securityProperties.getProperty(propName);

			if (className == null) {
				throw new SecurityAuthException(null, "Property:[" + propName + "] is not set.");
			}

			return Class.forName(className).newInstance();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityAuthException(e, null, "Service:" + propName + " failed to initial.");
		}
	}

	 

}
