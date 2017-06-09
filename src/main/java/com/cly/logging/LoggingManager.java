package com.cly.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
 

public class LoggingManager {

	private LoggingManager() {

	}

	public static Logger getLogger(String loggerName) {

		return LoggerFactory.getLogger(loggerName);

	}

	public static Logger getLogger(Class<Object> clazz) {

		return LoggerFactory.getLogger(clazz);

	}
	
	public static Logger getRootLogger() {

		return LoggerFactory.getLogger("root");

	}
	
	public static void systemErr(Object errMsg) {

		System.err.println(errMsg);

	}
	
	public static void systemErr(Exception e) {

		e.printStackTrace();

	}
	
	public static void systemInfo(Object info) {

		System.out.println(info);

	}

 

}
