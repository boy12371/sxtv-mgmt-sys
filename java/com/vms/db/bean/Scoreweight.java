package com.vms.db.bean;

import com.vms.db.bean.base.BaseScoreweight;



public class Scoreweight extends BaseScoreweight {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Scoreweight () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Scoreweight (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Scoreweight (
		java.lang.String id,
		java.lang.Float wieght) {

		super (
			id,
			wieght);
	}

/*[CONSTRUCTOR MARKER END]*/


}