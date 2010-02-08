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
	public Audiencescore (com.vms.db.bean.AudienceScorePK id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Audiencescore (
		com.vms.db.bean.AudienceScorePK id,
		java.util.Date dateExamine,
		java.lang.Integer result) {

		super (
			id,
			dateExamine,
			result);
	}

/*[CONSTRUCTOR MARKER END]*/

}
