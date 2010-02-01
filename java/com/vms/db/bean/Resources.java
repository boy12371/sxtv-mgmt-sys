package com.vms.db.bean;

import com.vms.db.bean.base.BaseResources;

public class Resources extends BaseResources {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Resources() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Resources(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Resources(java.lang.Integer id, java.lang.String url, java.lang.String comments) {

		super(id, url, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}