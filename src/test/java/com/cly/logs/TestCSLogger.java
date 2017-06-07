package com.cly.logs;

import java.io.IOException;

import org.junit.Test;

import com.cly.comm.client.config.ConfigClient;
import com.cly.logging.CLYLogger;
import com.cly.logging.CLYLoggerManager;

public class TestCSLogger {
	
	 
	public void testCSLogger(){
		
		CLYLoggerManager.initXMLConfig(TestCSLogger.class.getResource("/logs/logger.config.xml").getFile());
		
		CLYLogger logger=CLYLoggerManager.getLogger(TestCSLogger.class.getName()); 
		
		logger.fatal("saa");
		
	}

	@Test
	public void testCSLogger1() throws IOException{
		
		CLYLoggerManager.initPropertiesConfig(ConfigClient.getInputStream("/cloud.security/cloud.security.server.log4j.properties"));
		
		//CLYLoggerManager.initXMLConfig("C:/Users/SuperM/workspace-myprojects/AppConfig/dev/cloud.security/cloud.security.server.log4j.xml");
		
		CLYLogger logger=CLYLoggerManager.getRootLogger();
		
		logger.fatal("saa");
		
	}
	
}
