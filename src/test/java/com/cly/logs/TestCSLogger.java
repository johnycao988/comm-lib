package com.cly.logs;

 
import java.io.IOException;
 
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import com.cly.logging.LoggingManager;

public class TestCSLogger {
	
	 
 
	
	@Test
	public void testSLF4JLogger() throws IOException{
		
		String configFile=TestCSLogger.class.getResource("/logs/logger.config.xml").getFile();
		
		LoggingManager.initLog4j2Config(configFile);		 
		
		final  Logger logger  =  LoggerFactory.getLogger(LoggingManager.class);
		
		logger.error("test error!");
		
	}
	
}
