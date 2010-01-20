package com.vms.db.bean;

import com.vms.db.bean.base.BaseStatus;



public class Status extends BaseStatus {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Status () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Status (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Status (
		java.lang.Integer id,
		java.lang.String status,
		java.lang.String comments) {

		super (
			id,
			status,
			comments);
	}

/*[CONSTRUCTOR MARKER END]*/


}