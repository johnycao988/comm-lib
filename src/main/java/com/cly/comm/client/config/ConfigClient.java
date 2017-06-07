package com.cly.comm.client.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties; 

import com.cly.comm.client.http.HttpClient;
import com.cly.comm.client.http.HttpRequestParam;
import com.cly.comm.util.IOUtil;
import com.cly.comm.util.YamlParser;
import com.cly.logging.CLYLogger; 

public class ConfigClient {

	private static final String AUTH_CODE = "CONFIG.SERVICE.AUTH.CODE";
	private static final String CONFIG_SERVICE_URL = "CONFIG.SERVICE.URL";
	private static final String ROOT_CONFIG_PATH = "CONFIG.ROOT.PATH";

	private static String configServerUrl = init();
	private static String rootConfigPath;
	private static String authCode;

	private ConfigClient() {

	}

	private static String init() {

	
		authCode = System.getProperty(AUTH_CODE, null);

		String pss = "Property:[";
		String pse = "] of Config Client is not set.";

		if (authCode == null) {
			CLYLogger.systemErr(pss + AUTH_CODE + pse);
		}

		String serverUrl = System.getProperty(CONFIG_SERVICE_URL, null);

		if (serverUrl == null) {
			CLYLogger.systemErr(pss + CONFIG_SERVICE_URL + pse);
		} else {
			CLYLogger.systemInfo("Config server url is:" + serverUrl);
		}

		rootConfigPath = System.getProperty(ROOT_CONFIG_PATH, null);

		if (rootConfigPath == null) {
			CLYLogger.systemErr(pss + ROOT_CONFIG_PATH + pse);
		} else {
			CLYLogger.systemInfo("Config server root path is:" + rootConfigPath);
		}

		return serverUrl;
	}

	public static Properties getProperties(String configFile) throws IOException {

		Properties p = new Properties();
		p.load(getInputStream(configFile));
		return p;
	}

	public static YamlParser getYaml(String configFile) throws IOException {

		YamlParser yml = new YamlParser();
		yml.parse(getInputStream(configFile));
		return yml;
	}

	public static String getText(String configFile) throws IOException {

		return new String(getBytes(configFile), "UTF-8");
	}

	public static byte[] getBytes(String configFile) throws IOException {

		try (InputStream is = getInputStream(configFile);) {

			return IOUtil.getInputStreamBytes(is);
		}
	}

	public static InputStream getInputStream(String configFile) throws IOException {

		HttpRequestParam rp = new HttpRequestParam();

		if (authCode != null)
			rp.addParam("AUTH_CODE", authCode);

		rp.addParam("CONFIG_FILE_NAME", rootConfigPath + configFile);
		
		CLYLogger.systemInfo("A request to Config server:"+configServerUrl+", msg:"+rp.toString());
				 
		return HttpClient.getInputStream(configServerUrl, HttpClient.REQUEST_METHOD_POST, rp);
	}
	
	 

}
