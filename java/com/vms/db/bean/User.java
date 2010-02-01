package com.vms.db.bean;

import com.vms.db.bean.base.BaseUser;

public class User extends BaseUser {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public User() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public User(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public User(java.lang.Integer id, Employee employee, java.lang.String userName, java.lang.String userPass,
			java.lang.Integer status) {

		super(id, employee, userName, userPass, status);
	}

	/* [CONSTRUCTOR MARKER END] */

}