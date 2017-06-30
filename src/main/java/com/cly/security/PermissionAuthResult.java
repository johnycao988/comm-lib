package com.cly.security;

import java.util.ArrayList;
import java.util.List;

public class PermissionAuthResult {

	private List<AuthPermission> permissionList;

	public PermissionAuthResult(List<AuthPermission> permissionList) {

		this.permissionList = permissionList;

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

}
