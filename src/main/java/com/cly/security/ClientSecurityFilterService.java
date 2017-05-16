package com.cly.security;

import java.util.Properties;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cly.comm.client.http.HttpClient;
import com.cly.comm.client.http.HttpRequestParam;
import com.cly.comm.util.JSONResult;
import com.cly.comm.util.JSONUtil;
import com.cly.security.SecuConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClientSecurityFilterService implements ClientSecurityFilter {

	private static final String COOKIE_NAME_USER_ID = "#USER_ID";
	private static final String COOKIE_NAME_AUTH_CODE = "#AUTH_CODE";
	private String secuServerUrl = null;
	private String localUrl = null;

	@Override
	public boolean authenticateUser(ServletRequest request, ServletResponse response) {

		HttpServletRequest ret = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;

		String sid = ret.getSession(true).getId();

		String uid = geCookieValue(ret, sid + COOKIE_NAME_USER_ID);

		String authCode = geCookieValue(ret, sid + COOKIE_NAME_AUTH_CODE);

		if (authenticateUser(uid, authCode))
			return true;

		String inqAuthCode = request.getParameter(SecuConst.AUTH_INQ_CODE);

		if (inqAuthCode != null) {

			JSONObject msg = new JSONObject();
			msg.put(SecuConst.AUTH_INQ_CODE, inqAuthCode);
			JSONResult jr = this.requestServer(this.secuServerUrl + "/rest/user/inqAuthCode", msg.toString());
			if (jr.isSuccess()) {

				msg = jr.getJSONObject();
				uid = JSONUtil.getString(msg, SecuConst.USER_ID);
				authCode = JSONUtil.getString(msg, SecuConst.AUTH_CODE);

				Cookie cookie = new Cookie(sid + COOKIE_NAME_USER_ID, uid);
				res.addCookie(cookie);
				cookie = new Cookie(sid + COOKIE_NAME_AUTH_CODE, authCode);
				res.addCookie(cookie);
				return true;
			}
		}

		return false;
	}

	@Override
	public void initProperties(Properties p) {
		this.secuServerUrl = p.getProperty("cloud.security.server.url");
		if (this.secuServerUrl == null) {
			Logger.getGlobal().warning("Security Client Property:cloud.security.server.url has not beent set.");
		}

		this.localUrl = p.getProperty("cloud.security.client.url");
		if (this.localUrl == null) {
			Logger.getGlobal().warning("Security Client Property:cloud.security.client.url has not beent set.");
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

	@Override
	public String getLoginUrl(ServletRequest request, ServletResponse response) {

		HttpServletRequest rst = (HttpServletRequest) request;

		return this.secuServerUrl + "/login/authLogin.jsp?" + SecuConst.AUTH_REDIRECT_URL + "=" + this.localUrl
				+ rst.getRequestURI();

	}

	@Override
	public boolean authenticateUser(String userId, String authCode) {

		if (userId != null && authCode != null) {

			JSONObject msg = new JSONObject();
			msg.put(SecuConst.USER_ID, userId);
			msg.put(SecuConst.AUTH_CODE, authCode);
			JSONResult jr = this.requestServer(this.secuServerUrl + "/rest/user/validate", msg.toString());
			if (jr.isSuccess())
				return true;
		}

		return false;
	}

	@Override
	public boolean authUserGroups(ServletRequest request, ServletResponse response, String[] grpNames) {
		
		HttpServletRequest ret = (HttpServletRequest) request;

		String sid = ret.getSession().getId();

		String uid = geCookieValue(ret, sid + COOKIE_NAME_USER_ID);

		String authCode = geCookieValue(ret, sid + COOKIE_NAME_AUTH_CODE);

		return authUserGroups(uid, authCode, grpNames);
	}

	@Override
	public boolean authUserGroups(String userId, String authCode, String[] grpNames) {

		if (userId == null || authCode == null || grpNames == null || grpNames.length <= 0)
			return false;

		JSONObject msg = new JSONObject();

		JSONArray ja = new JSONArray();

		for (String gn : grpNames)
			ja.add(gn);

		msg.put(SecuConst.USER_ID, userId);

		msg.put(SecuConst.AUTH_CODE, authCode);

		msg.put(SecuConst.AUTH_USER_GROUPS, ja);

		JSONResult jr = this.requestServer(this.secuServerUrl + "/rest/user/authUserGrps", msg.toString());

		return jr.isSuccess();

	}

}
