package com.vms.db.bean;

import com.vms.db.bean.base.BaseRoleResource;



public class RoleResource extends BaseRoleResource {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public RoleResource () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public RoleResource (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public RoleResource (
		java.lang.Integer id,
		com.vms.db.bean.Role roleid,
		com.vms.db.bean.Resources resourceid,
		java.lang.String comments) {

		super (
			id,
			roleid,
			resourceid,
			comments);
	}

/*[CONSTRUCTOR MARKER END]*/


}