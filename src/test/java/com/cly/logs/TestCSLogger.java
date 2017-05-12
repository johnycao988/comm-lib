package com.cly.logs;

import org.junit.Test;

import com.cly.logging.CLYLogger;
import com.cly.logging.CLYLoggerManager;

public class TestCSLogger {
	
	@Test
	public void testCSLogger(){
		
		CLYLoggerManager.initXMLConfig(TestCSLogger.class.getResource("/logs/logger.config.xml").getFile());
		
		CLYLogger logger=CLYLoggerManager.getLogger(TestCSLogger.class.getName()); 
		
		logger.fatal("saa");
		
	}

}
