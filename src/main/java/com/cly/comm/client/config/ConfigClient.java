package com.cly.comm.client.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.w3c.dom.Document;

import com.cly.comm.client.http.HttpClient;
import com.cly.comm.client.http.HttpRequestParam;
import com.cly.comm.intf.CLYProperties;
import com.cly.comm.util.IOUtil;
import com.cly.comm.util.XMLUtil;
import com.cly.comm.util.YamlParser;
import com.cly.logging.LoggingManager;

public class ConfigClient {

	public static final String AUTH_CODE = "CLOUD.CONFIG.SERVICE.AUTH.CODE";
	public static final String CONFIG_SERVICE_URL = "CLOUD.CONFIG.SERVICE.URL";
	public static final String ROOT_CONFIG_PATH = "CLOUD.CONFIG.ROOT.PATH";

	private static String rootPath;
	private static String authCode;
	private static String serverUrl;

	private ConfigClient() {

	}

	public static void init(CLYProperties prop) {

		String configAuthCode = prop.getProperty(ConfigClient.AUTH_CODE);
		String configServerUrl = prop.getProperty(ConfigClient.CONFIG_SERVICE_URL);
		String rootConfigPath = prop.getProperty(ConfigClient.ROOT_CONFIG_PATH);
		init(configAuthCode, configServerUrl, rootConfigPath);

	}

	private static void init(String configAuthCode, String configServerUrl, String rootConfigPath) {

		authCode = configAuthCode;
		if (authCode == null)
			authCode = System.getProperty(AUTH_CODE, null);

		String pss = "Property:[";
		String pse = "] of Config Client is not set.";

		if (authCode == null) {
			LoggingManager.systemErr(pss + AUTH_CODE + pse);
		}

		serverUrl = configServerUrl;

		if (serverUrl == null)
			serverUrl = System.getProperty(CONFIG_SERVICE_URL, null);

		if (serverUrl == null) {
			LoggingManager.systemErr(pss + CONFIG_SERVICE_URL + pse);
		} else {
			LoggingManager.systemInfo("Config server url is:" + serverUrl);
		}

		rootPath = rootConfigPath;

		if (rootPath == null)
			rootPath = System.getProperty(ROOT_CONFIG_PATH, null);

		if (rootPath == null) {

			LoggingManager.systemErr(pss + ROOT_CONFIG_PATH + pse);

		} else {

			LoggingManager.systemInfo("Config server root path is:" + rootPath);

		}

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

	public static Document getDocuement(String configFile) throws IOException {

		try {
			try (InputStream is = getInputStream(configFile);) {

				return XMLUtil.parse(is);
			}
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public static InputStream getInputStream(String configFile) throws IOException {

		HttpRequestParam rp = new HttpRequestParam();

		rp.addParam("CONFIG_FILE_NAME", rootPath + configFile);

		LoggingManager.systemInfo("A request to Config server:" + serverUrl + ", msg:" + rp.toString());

		if (authCode != null)
			rp.addParam("AUTH_CODE", authCode);

		return HttpClient.getInputStream(serverUrl, HttpClient.REQUEST_METHOD_POST, rp);
	}

}
