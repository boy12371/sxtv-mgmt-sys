package com.vms.db.bean;

import com.vms.db.bean.base.BaseAudiencescore;



public class Audiencescore extends BaseAudiencescore {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Audiencescore () {
		super();
	}

	/**
	 * Constructor for required fields
	 */
	public Audiencescore (
		com.vms.db.bean.Audience audienceID,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date dateExamine,
		java.lang.Integer result) {

		super (
			audienceID,
			vedioID,
			dateExamine,
			result);
	}

/*[CONSTRUCTOR MARKER END]*/

}
