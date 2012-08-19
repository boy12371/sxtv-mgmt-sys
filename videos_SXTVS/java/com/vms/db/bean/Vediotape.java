package com.vms.db.bean;

import com.vms.db.bean.base.BaseVediotape;

public class Vediotape extends BaseVediotape {
	private static final long serialVersionUID = 1L;
	
	private float score;
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
			java.lang.String vedioName, java.util.Date dateComing, java.util.Date dateInput,java.util.Date dateStore) {

		super(id, topic, inputer, status, subject, companyID, vedioName, dateComing, dateInput, dateStore);
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}


	/* [CONSTRUCTOR MARKER END] */

}