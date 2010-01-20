package com.vms.db.bean;

import com.vms.db.bean.base.BaseAuditing;

public class Auditing extends BaseAuditing {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Auditing() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Auditing(java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Auditing(java.lang.String id, com.vms.db.bean.Topic topic,
			com.vms.db.bean.User inputer, com.vms.db.bean.Status status,
			com.vms.db.bean.Subject subject, com.vms.db.bean.Company companyID,
			java.lang.String vedioName, java.util.Date dateComing,
			java.util.Date dateInput) {

		super(id, topic, inputer, status, subject, companyID, vedioName,
				dateComing, dateInput);
	}

	/* [CONSTRUCTOR MARKER END] */

}