package com.vms.db.bean;

import com.vms.db.bean.base.BaseUserRole;

public class UserRole extends BaseUserRole {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public UserRole() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UserRole(UserRolePK id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public UserRole(UserRolePK id, java.lang.String comments) {
		super(id, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}