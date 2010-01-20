package com.vms.db.bean;

import com.vms.db.bean.base.BaseVedioscore;



public class Vedioscore extends BaseVedioscore {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Vedioscore () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Vedioscore (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Vedioscore (
		java.lang.Integer id,
		com.vms.db.bean.Vediotape vedioID,
		java.lang.Float storyScore,
		java.lang.Float techScore,
		java.lang.Float performScore,
		java.lang.Float innovateScore,
		java.lang.Float score,
		java.lang.Integer audiendceVote,
		java.lang.String award,
		java.lang.Float precision,
		java.lang.String purchase) {

		super (
			id,
			vedioID,
			storyScore,
			techScore,
			performScore,
			innovateScore,
			score,
			audiendceVote,
			award,
			precision,
			purchase);
	}

/*[CONSTRUCTOR MARKER END]*/


}