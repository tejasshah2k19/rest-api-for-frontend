package com.model;

public class RoleBean {

	public enum Role {

		ADMIN(1), USER(2);
		Role(int roleId) {
			this.roleId = roleId;
		}

		int roleId;
		
		public int getRoleId() {
			return roleId;
		}
	}
}
