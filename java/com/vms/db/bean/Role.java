package com.vms.db.bean;

import com.vms.db.bean.base.BaseRole;

public class Role extends BaseRole {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Role() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Role(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Role(java.lang.Integer id, java.lang.String name, java.lang.String comments) {

		super(id, name, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}