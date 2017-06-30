package com.cly.security; 

public class UserInfo {

	private String userId;
	private String userName;
	private String authCode;
	private String[] userGroups;
	
	public UserInfo(String userId,String userName, String authCode, String[] userGroups) {
		
		this.userId=userId;
		this.userName=userName;
		this.authCode=authCode;
		this.userGroups=userGroups;
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

}
