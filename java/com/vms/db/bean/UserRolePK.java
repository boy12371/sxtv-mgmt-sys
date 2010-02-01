package com.vms.db.bean;

import com.vms.db.bean.base.BaseUserRolePK;

public class UserRolePK extends BaseUserRolePK {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public UserRolePK() {
	}

	public UserRolePK(Role roleid, User userid) {

		super(roleid, userid);
	}
	/* [CONSTRUCTOR MARKER END] */

}