package com.cly.security;

import java.util.ArrayList;
import java.util.List;

import com.cly.comm.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PermissionAuthResult {

	private List<AuthPermission> permissionList;
	private static final String PERMISSION_LIST = "permissionList";

	public PermissionAuthResult(List<AuthPermission> permissionList) {

		this.permissionList = permissionList;

	}

	public PermissionAuthResult(JSONObject jo) {

		JSONArray ja = JSONUtil.getJSONArray(jo, PERMISSION_LIST);

		if (ja != null) {

			ArrayList<AuthPermission> al = new ArrayList<AuthPermission>();

			for (int i = 0; i < ja.size(); i++) {

				JSONObject jp = ja.getJSONObject(i);

				AuthPermission ap = new AuthPermission(jp);

				al.add(ap);
			}
			
			this.permissionList=al;

		}
		

	}

	public AuthPermission[] getAllPermissions() {

		return permissionList.toArray(new AuthPermission[0]);
	}

	public AuthPermission[] getFailedPermissions() {

		return checkPermissions(false);
	}

	public AuthPermission[] getPassedPermissions() {

		return checkPermissions(true);
	}

	private AuthPermission[] checkPermissions(boolean isPermitted) {

		ArrayList<AuthPermission> alPerm = new ArrayList<AuthPermission>();

		for (AuthPermission ap : this.permissionList) {

			if (ap.isPermitted() == isPermitted)
				alPerm.add(ap);
		}

		return alPerm.toArray(new AuthPermission[0]);
	}

	public boolean isAllPermissionsPassed() {

		for (AuthPermission ap : this.permissionList) {

			if (!ap.isPermitted())
				return false;
		}

		return true;
	}

	@Override
	public String toString() {

		JSONObject jo = new JSONObject();

		JSONArray ja = new JSONArray();

		if (permissionList != null) {

			for (AuthPermission ap : permissionList) {
				ja.add(ap);
			}

		}

		jo.put(PERMISSION_LIST, ja);

		return jo.toString();
	}

}
