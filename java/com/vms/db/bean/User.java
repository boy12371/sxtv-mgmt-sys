package com.vms.db.bean;

import com.vms.db.bean.base.BaseUser;



public class User extends BaseUser {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public User () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public User (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public User (
		java.lang.Integer id,
		com.vms.db.bean.Employee employee,
		java.lang.String userName,
		java.lang.String userPass) {

		super (
			id,
			employee,
			userName,
			userPass);
	}

/*[CONSTRUCTOR MARKER END]*/


}