package com.vms.db.bean;

import com.vms.db.bean.base.BasePlayorder;

public class Playorder extends BasePlayorder {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Playorder() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Playorder(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Playorder(java.lang.Integer id, com.vms.db.bean.User auditor, com.vms.db.bean.Vediotape vedioID,
			java.util.Date playDate, java.util.Date arrangeDate) {

		super(id, auditor, vedioID, playDate, arrangeDate);
	}

	/* [CONSTRUCTOR MARKER END] */

}