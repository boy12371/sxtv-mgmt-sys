package com.vms.db.bean;

import java.util.Set;

import com.vms.db.bean.base.BaseVediotape;

public class Vediotape extends BaseVediotape {
	private static final long serialVersionUID = 1L;
	
	

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Vediotape() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Vediotape(java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Vediotape(java.lang.String id, com.vms.db.bean.Topic topic, com.vms.db.bean.User inputer,
			com.vms.db.bean.Status status, com.vms.db.bean.Subject subject, com.vms.db.bean.Company companyID,
			java.lang.String vedioName, java.util.Date dateComing, java.util.Date dateInput) {

		super(id, topic, inputer, status, subject, companyID, vedioName, dateComing, dateInput);
	}

	

	/* [CONSTRUCTOR MARKER END] */

}