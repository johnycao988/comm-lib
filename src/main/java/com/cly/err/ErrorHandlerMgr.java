package com.cly.err;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import com.cly.comm.util.XMLUtil;

public class ErrorHandlerMgr {

	private static HashMap<String, HashMap<String, String>> hmErrHandler = new HashMap<String, HashMap<String, String>>();

	private static String DEFAULT_LAN = "EN";

	private ErrorHandlerMgr() {

	}


	public static void clear() {

		Iterator<HashMap<String, String>> it = hmErrHandler.values().iterator();

		while (it.hasNext()) {

			HashMap<String, String> hmc = it.next();

			hmc.clear();

		}

		hmErrHandler.clear();

		DEFAULT_LAN = "EN";

	}

	public static void addConfigFile(String fileName) throws FileNotFoundException, SAXException {

		FileInputStream is = new FileInputStream(fileName);

		addConfigFile(is);

	}

	public static void addConfigFile(InputStream is) throws SAXException {

		Document doc = XMLUtil.parse(is);

		Element eroot = doc.getDocumentElement();

		String dl = eroot.getAttribute("deafultLanguage");

		if (dl != null && dl.trim().length() > 0)
			DEFAULT_LAN = dl.trim().toUpperCase();

		Element[] eps = XMLUtil.getChildElements(eroot);

		for (Element ep : eps) {

			String errCode = ep.getNodeName();

			Element[] ecs = XMLUtil.getChildElements(ep);

			for (Element ec : ecs) {

				String lan = ec.getNodeName();

				String errMsg = ec.getAttribute("errMsg");

				addErrMsg(lan, errCode, errMsg);
			}

		}

	}

	public static ErrorHandler getErrorHandler() {

		return getEH(DEFAULT_LAN);

	}

	public static ErrorHandler getErrorHandler(String language) {

		return getEH(language);

	}

	private static ErrorHandler getEH(String language) {

		String lang = language;

		if (lang != null)
			lang = lang.trim().toUpperCase();

		HashMap<String, String> hmEC = hmErrHandler.get(lang);

		if (hmEC != null)
			return new ErrorHandlerImpl(lang, hmEC);

		else if (lang != null && !lang.equals(DEFAULT_LAN))
			return getEH(DEFAULT_LAN);

		return null;

	}

	private static void addErrMsg(String language, String errCode, String errMsg) {

		String lang = language.trim().toUpperCase();

		HashMap<String, String> hmEC = hmErrHandler.get(lang);

		if (hmEC == null) {
			hmEC = new HashMap<String, String>();
			hmErrHandler.put(lang, hmEC);
		}

		hmEC.put(errCode, errMsg);

	}

}

class ErrorHandlerImpl implements ErrorHandler {

	private String lang;

	private HashMap<String, String> hmEH;

	public ErrorHandlerImpl(String lang, HashMap<String, String> hmEH) {

		this.lang = lang;

		this.hmEH = hmEH;
	}

	@Override
	public String getErrorMessage(String errCode, String... params) {

		String errMsg = hmEH.get(errCode);

		if (params == null || errMsg == null)
			return errMsg;

		String reg = "\\[\\%\\]";

		for (String p : params) {

			errMsg = errMsg.replaceFirst(reg, p);

		}

		return "Error [" + errCode + "]: " + errMsg;

	}

	@Override
	public String getLanguage() {

		return this.lang;
	}

}
