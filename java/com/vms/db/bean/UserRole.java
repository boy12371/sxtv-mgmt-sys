package com.vms.db.bean;

import com.vms.db.bean.base.BaseUserRole;



public class UserRole extends BaseUserRole {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public UserRole () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UserRole (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public UserRole (
		java.lang.Integer id,
		com.vms.db.bean.Role roleid,
		com.vms.db.bean.User userid,
		java.lang.String comments) {

		super (
			id,
			roleid,
			userid,
			comments);
	}

/*[CONSTRUCTOR MARKER END]*/


}