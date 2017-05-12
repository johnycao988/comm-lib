package com.cly.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class CLYLoggerManager {

	 
	public static void initXMLConfig(String configXMLFile) {

		DOMConfigurator.configure(configXMLFile);
		//PropertyConfigurator.configure(configFile);
	 
	}
	
	public static void initPropertiesConfig(String configPropFile) {
		 
		PropertyConfigurator.configure(configPropFile);
	 
	}

	public static CLYLogger getLogger(String loggerName) {

		return new CLYLogger(Logger.getLogger(loggerName));

	}
	
	public static CLYLogger getRootLogger() {

		return new CLYLogger(Logger.getRootLogger());

	}
	
 
}
