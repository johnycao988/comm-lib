package com.cly.security;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.cly.comm.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserInfo {

	private String userId;
	private String userName;
	private String authCode;
	private String[] userGroups;
	private String loginTime;
	

	public UserInfo(String userId, String userName, String authCode, String[] userGroups) {

		this.userId = userId;
		this.userName = userName;
		this.authCode = authCode;
		this.userGroups = userGroups;
		initLoginTime();
	}
	
	private void initLoginTime(){
		
		Calendar cd = Calendar.getInstance();  
		SimpleDateFormat sdf = new SimpleDateFormat("EEE  yyyy MM dd HH:mm:ss 'GMT'", Locale.US);  
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));  
		this.loginTime = sdf.format(cd.getTime()); 

		
	}

	public UserInfo(JSONObject jsonUserInfo) {

		this.userId = JSONUtil.getString(jsonUserInfo, SecuConst.USER_ID);
		this.userName = JSONUtil.getString(jsonUserInfo, SecuConst.USER_NAME);
		this.authCode = JSONUtil.getString(jsonUserInfo, SecuConst.AUTH_CODE);
		this.loginTime=JSONUtil.getString(jsonUserInfo, "loginTime");
		
		JSONArray ja = JSONUtil.getJSONArray(jsonUserInfo, SecuConst.AUTH_USER_GROUPS);
		
		if (ja != null && ja.size() > 0)
			this.userGroups = (String[]) ja.toArray(new String[0]);

	}

	public String getUserId() {

		return userId;
	}

	public String getUserName() {

		return this.userName;

	}

	public String getAuthCode() {

		return this.authCode;

	}

	public String[] getUserGroups() {

		return this.userGroups;

	}

	public String getLoginTime() {

		return this.loginTime;

	}
	
	@Override
	public String toString() {

		JSONObject jo = new JSONObject();

		jo.put(SecuConst.USER_ID, this.userId);

		jo.put(SecuConst.USER_NAME, this.userName);

		jo.put(SecuConst.AUTH_CODE, this.authCode);
		
		jo.put("loginTime", this.loginTime);

		JSONArray ja = new JSONArray();

		if (this.userGroups != null)
			for (String sg : this.userGroups)
				ja.add(sg);

		jo.put(SecuConst.AUTH_USER_GROUPS, ja);

		return jo.toString();

	}

}
