package com.vms.db.bean;

import com.vms.db.bean.base.BaseAudiencescore;



public class Audiencescore extends BaseAudiencescore {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Audiencescore () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Audiencescore (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Audiencescore (
		java.lang.Integer id,
		com.vms.db.bean.Audience audienceID,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date dateExamine,
		java.lang.Integer result) {

		super (
			id,
			audienceID,
			vedioID,
			dateExamine,
			result);
	}

/*[CONSTRUCTOR MARKER END]*/

}
