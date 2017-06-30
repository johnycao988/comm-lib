package com.cly.security;

import com.cly.comm.util.JSONUtil;

import net.sf.json.JSONObject;

public class AuthPermission {

	private String name;

	private boolean isPermitted;

	public AuthPermission(String permissionName, boolean isPermitted) {

		this.name = permissionName;

		this.isPermitted = isPermitted;
	}

	public AuthPermission(JSONObject jo) {

		this.name = JSONUtil.getString(jo, "name");

		this.isPermitted = JSONUtil.getBoolean(jo, "isPermittied");

	}

	public String getName() {
		return name;
	}

	public boolean isPermitted() {
		return isPermitted;
	}

	@Override
	public String toString() {

		JSONObject jo = new JSONObject();
		jo.put("name", this.name);
		jo.put("isPermittied", this.isPermitted);
		return jo.toString();
	}

}
