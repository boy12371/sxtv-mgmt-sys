package com.vms.db.bean;

import com.vms.db.bean.base.BaseAuditing;

public class Auditing extends BaseAuditing {
	private static final long serialVersionUID = 1L;

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public Auditing () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Auditing (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Auditing (
		java.lang.Integer id,
		com.vms.db.bean.Status result,
		com.vms.db.bean.User auditor,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date auditDate) {

		super (
			id,
			result,
			auditor,
			vedioID,
			auditDate);
	}

	/*[CONSTRUCTOR MARKER END]*/

}