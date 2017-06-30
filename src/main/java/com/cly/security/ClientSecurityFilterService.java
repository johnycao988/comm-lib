package com.cly.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.cly.comm.client.http.HttpClient;
import com.cly.comm.client.http.HttpRequestParam;
import com.cly.comm.util.JSONResult;
import com.cly.comm.util.JSONUtil;
import com.cly.logging.LoggingManager;
import com.cly.security.SecuConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClientSecurityFilterService implements ClientSecurityFilter {

	private static final String COOKIE_NAME_USER_ID = "CLOUD.SECURITY.FILTER#USER_ID";

	private static final String COOKIE_NAME_AUTH_CODE = "CLOUD.SECURITY.FILTER#AUTH_CODE";

	private String secuServerUrl = null;

	private String localUrl = null;

	private Logger logger;

	private ArrayList<String> excludeUris = new ArrayList<String>();

	@Override
	public boolean authenticateUser(HttpServletRequest request, HttpServletResponse response,
			boolean forwardUserLoginPage) throws IOException {

		String reqUri = request.getServletPath();

		for (String s : this.excludeUris) {

			if (s.equals(reqUri)) {

				return true;
			}

		}

	 
		String uid = geCookieValue(request,  COOKIE_NAME_USER_ID);

		String authCode = geCookieValue(request,  COOKIE_NAME_AUTH_CODE);

		if (isInqAuthCodeRequest(request, response))
			return true;

		if (!authenticateUser(uid, authCode)) {

			if (forwardUserLoginPage) {

				String loginUrl = getLoginUrl(request);
				response.sendRedirect(loginUrl);

			}

			return false;

		}

		return true;

	}

	@Override
	public void init(Properties p) {

		this.excludeUris.clear();

		String loggerName = p.getProperty("cloud.security.client.filter.logger.name");
		if (loggerName != null && loggerName.trim().length() > 0) {

			logger = LoggingManager.getLogger(loggerName);

		} else {

			logger = LoggingManager.getRootLogger();

			this.logger.warn("Security Client Property:cloud.security.client.filter.logger.name has not beent set.");
		}

	 
		this.secuServerUrl = p.getProperty("cloud.security.server.url");

		if (this.secuServerUrl == null) {

			this.logger.warn("Security Client Property:cloud.security.server.url has not beent set.");

		}

		this.localUrl = p.getProperty("cloud.security.client.url");

		if (this.localUrl == null) {

			this.logger.warn("Security Client Property:cloud.security.client.url has not beent set.");

		}

		String se = p.getProperty("cloud.security.client.filter.exclude.uris");

		if (se != null && se.trim().length() > 0) {

			String[] uris = se.trim().split(";");

			for (String uri : uris) {

				this.logger.debug("Exinclude filter uri:" + uri);
				this.addExcludeUri(uri);
			}

		} else {
			this.logger.warn("Security Client Property:cloud.security.client.filter.exclude.uris has not beent set.");
		}

	}

	private JSONResult requestServer(String serverUrl, String msg) {

		try {

			HttpRequestParam rp = new HttpRequestParam();

			rp.addParam(HttpRequestParam.REQ_JSON_MESSAGE_NAME, msg);

			String res = HttpClient.request(serverUrl, HttpClient.REQUEST_METHOD_POST, rp);

			return new JSONResult(res);

		} catch (Exception e) {

			return new JSONResult(JSONUtil.initFailed(e));

		}

	}

	private String geCookieValue(HttpServletRequest rs, String cookieName) {

		Cookie[] cks = rs.getCookies();

		if (cks != null)
			for (Cookie ck : cks) {

				String ckn = ck.getName();

				if (ckn.equals(cookieName))
					return ck.getValue();

			}

		return null;
	}

	private String getLoginUrl(HttpServletRequest request) {

		return this.secuServerUrl + "/login?" + SecuConst.AUTH_REDIRECT_URL + "=" + this.localUrl
				+ request.getRequestURI();

	}

	@Override
	public boolean authenticateUser(String userId, String authCode) {

		if (userId != null && authCode != null) {

			JSONObject msg = new JSONObject();

			msg.put(SecuConst.USER_ID, userId);

			msg.put(SecuConst.AUTH_CODE, authCode);

			JSONResult jr = this.requestServer(this.secuServerUrl + "/user/validate", msg.toString());

			if (jr.isSuccess())
				return true;
		}

		return false;
	}

	@Override
	public boolean accessPermmission(HttpServletRequest request, HttpServletResponse response,
			String[] permissionNames) {

		 
		String uid = geCookieValue(request,  COOKIE_NAME_USER_ID);

		String authCode = geCookieValue(request,  COOKIE_NAME_AUTH_CODE);

		return accessPermmission(uid, authCode, permissionNames);
	}

	@Override
	public boolean accessPermmission(String userId, String authCode, String[] permissionNames) {

		if (userId == null || authCode == null || permissionNames == null || permissionNames.length <= 0)
			return false;

		JSONObject msg = new JSONObject();

		JSONArray ja = new JSONArray();

		for (String pn : permissionNames)
			ja.add(pn);

		msg.put(SecuConst.USER_ID, userId);

		msg.put(SecuConst.AUTH_CODE, authCode);

		msg.put(SecuConst.AUTH_USER_GROUPS, ja);

		JSONResult jr = this.requestServer(this.secuServerUrl + "/user/authAccessPermmison", msg.toString());

		return jr.isSuccess();

	}

	private boolean isInqAuthCodeRequest(ServletRequest request, ServletResponse response) {

		HttpServletRequest ret = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;

		String inqAuthCode = request.getParameter(SecuConst.AUTH_INQ_CODE);

		if (inqAuthCode != null) {

			 
			String uid = geCookieValue(ret,  COOKIE_NAME_USER_ID);

			String authCode = geCookieValue(ret,  COOKIE_NAME_AUTH_CODE);

			if (uid != null && authCode != null)
				return false;

			JSONObject msg = new JSONObject();

			msg.put(SecuConst.AUTH_INQ_CODE, inqAuthCode);

			JSONResult jr = this.requestServer(this.secuServerUrl + "/user/inqAuthCode", msg.toString());

			if (jr.isSuccess()) {

				msg = jr.getJSONObject();

				uid = JSONUtil.getString(msg, SecuConst.USER_ID);

				authCode = JSONUtil.getString(msg, SecuConst.AUTH_CODE);

				Cookie cookie = new Cookie(COOKIE_NAME_USER_ID, uid);

				cookie.setMaxAge(-1);

				res.addCookie(cookie);

				cookie = new Cookie( COOKIE_NAME_AUTH_CODE, authCode);

				cookie.setMaxAge(-1);

				res.addCookie(cookie);

				return true;
			}
		}

		return false;
	}

	@Override
	public ClientSecurityFilter addExcludeUri(String uri) {

		this.excludeUris.add(uri);
		return this;
	}

}
