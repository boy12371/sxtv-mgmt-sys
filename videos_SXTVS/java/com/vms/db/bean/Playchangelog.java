package com.vms.db.bean;

import com.vms.db.bean.base.BasePlaychangelog;

public class Playchangelog extends BasePlaychangelog {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Playchangelog() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Playchangelog(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Playchangelog (
		java.lang.Integer id,
		com.vms.db.bean.User auditor,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date date) {

		super (
			id,
			auditor,
			vedioID,
			date);
	}

	/* [CONSTRUCTOR MARKER END] */

}