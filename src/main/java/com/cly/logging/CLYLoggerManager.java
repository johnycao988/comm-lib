package com.cly.logging;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class CLYLoggerManager {

	private static boolean bInit = false;

	private CLYLoggerManager() {

	}

	public static void initXMLConfig(String configXMLFile) {

		if (!bInit) {

			DOMConfigurator.configure(configXMLFile);
			
			bInit = true;
		}

	}

	public static void initPropertiesConfig(String configPropFile) {

		if (!bInit) {

			PropertyConfigurator.configure(configPropFile);

			bInit = true;
		}

	}

	public static void initPropertiesConfig(InputStream inputConfig) {

		if (!bInit) {

			PropertyConfigurator.configure(inputConfig);

			bInit = true;
		}

	}

	public static CLYLogger getLogger(String loggerName) {

		return new CLYLogger(Logger.getLogger(loggerName));
		
	}

	public static CLYLogger getRootLogger() {

		return new CLYLogger(Logger.getRootLogger());

	}

}
