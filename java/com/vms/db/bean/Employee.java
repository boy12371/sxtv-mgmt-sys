package com.vms.db.bean;

import com.vms.db.bean.base.BaseEmployee;

public class Employee extends BaseEmployee {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Employee() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Employee(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Employee(java.lang.Integer id, java.lang.String name, java.util.Date birthday, java.util.Date contractDate,
			java.lang.Integer gender, java.lang.String tel, java.lang.Integer status, java.lang.String comments) {

		super(id, name, birthday, contractDate, gender, tel, status, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}