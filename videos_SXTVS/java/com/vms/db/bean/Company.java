package com.vms.db.bean;

import com.vms.db.bean.base.BaseCompany;

public class Company extends BaseCompany {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Company() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Company(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Company(java.lang.Integer id, java.lang.String companyName, java.lang.String registrationNo,
			java.lang.String phone, java.lang.String contactPerson, java.lang.String comments, java.lang.Integer status) {

		super(id, companyName, registrationNo, phone, contactPerson, comments,status);
	}

	/* [CONSTRUCTOR MARKER END] */

}