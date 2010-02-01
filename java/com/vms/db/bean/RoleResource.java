package com.vms.db.bean;

import com.vms.db.bean.base.BaseRoleResource;

public class RoleResource extends BaseRoleResource {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public RoleResource() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public RoleResource(RoleResourcePK id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public RoleResource(RoleResourcePK id, java.lang.String comments) {

		super(id, comments);
	}

	/* [CONSTRUCTOR MARKER END] */

}