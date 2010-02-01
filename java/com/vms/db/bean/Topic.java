package com.vms.db.bean;

import com.vms.db.bean.base.BaseTopic;

public class Topic extends BaseTopic {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Topic() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Topic(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Topic(java.lang.Integer id, java.lang.String topicName, java.lang.String comments) {

		super(id, topicName, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}