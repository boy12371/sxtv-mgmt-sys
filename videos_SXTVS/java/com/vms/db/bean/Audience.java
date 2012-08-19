package com.vms.db.bean;

import com.vms.db.bean.base.BaseAudience;



public class Audience extends BaseAudience {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Audience () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Audience (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Audience (
		java.lang.Integer id,
		java.lang.String name) {

		super (
			id,
			name);
	}

/*[CONSTRUCTOR MARKER END]*/


}