package com.vms.db.bean;

import com.vms.db.bean.base.BaseSubject;

public class Subject extends BaseSubject {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Subject() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Subject(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Subject(java.lang.Integer id, java.lang.String subjectName,java.lang.Integer status, java.lang.String comments) {

		super(id, subjectName, status, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}
