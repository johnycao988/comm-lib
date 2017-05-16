package com.cly.security;

import java.util.Properties;

import com.cly.comm.client.config.ConfigClient;
import com.cly.logging.CLYLoggerManager;
import com.cly.security.SecurityAuthException;

public class ClientSecurityServiceManager {
	
	private static Properties securityProperties = null;
	private static ClientSecurityFilter secuFilterService = null;
 
	private ClientSecurityServiceManager() {

	}

	public static Properties getProperties() {

		try {
			if (securityProperties == null)
				securityProperties = ConfigClient.getProperties("cloud.security.client.properties");
			return securityProperties;
		} catch (Exception e) {

			CLYLoggerManager.getRootLogger().fatalException(e);
			securityProperties = new Properties();
			return securityProperties;
		}

	}

	public static String refresh(){
		securityProperties = null;
		secuFilterService = null; 
		return "Security Client Refresh completed.";
	}

	public static ClientSecurityFilter getClientSecurityFilterService() throws SecurityAuthException {

		if (secuFilterService == null) {
			secuFilterService = (ClientSecurityFilter) createServiceInstance("cloud.security.client.filter.service");

			secuFilterService.initProperties(getProperties()); 
		}

		return secuFilterService;

	} 

	private static Object createServiceInstance(String propName) throws SecurityAuthException {

		try {

			Properties p = getProperties();

			String className = p.getProperty(propName);

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
