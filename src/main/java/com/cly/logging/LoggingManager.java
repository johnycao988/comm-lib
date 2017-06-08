package com.cly.logging;

import java.io.FileInputStream;  
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingManager {

	private LoggingManager() {

	}

	public static void initLog4j2Config(String configFile) throws IOException {

		try (FileInputStream is = new FileInputStream(configFile)) {

			initLog4j2Config(is);
			
		}

	}

	public static void initLog4j2Config(InputStream inputConfig) throws IOException {

		ConfigurationSource cs = new ConfigurationSource(inputConfig);

		Configurator.initialize(null, cs);

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
	
	public static void systemInfo(Object info) {

		System.out.println(info);

	}

}
