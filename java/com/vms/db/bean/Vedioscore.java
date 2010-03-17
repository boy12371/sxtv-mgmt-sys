package com.vms.db.bean;

import com.vms.db.bean.base.BaseVedioscore;

public class Vedioscore extends BaseVedioscore {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
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
		com.vms.db.bean.Vediotape vedio,
		com.vms.db.bean.User examiner,
		com.vms.db.bean.User operator,
		java.lang.Float storyScore,
		java.lang.Float techScore,
		java.lang.Float performScore,
		java.lang.Float innovateScore,
		java.lang.Float score,
		java.lang.Integer award,
		java.lang.Integer orientation,
		java.lang.Integer purchase,
		java.util.Date dateExamine) {

		super (
			id,
			vedio,
			examiner,
			operator,
			storyScore,
			techScore,
			performScore,
			innovateScore,
			score,
			award,
			orientation,
			purchase,
			dateExamine);
	}

	/* [CONSTRUCTOR MARKER END] */
}
