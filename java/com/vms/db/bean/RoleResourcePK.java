package com.vms.db.bean;

import com.vms.db.bean.base.BaseRoleResourcePK;

public class RoleResourcePK extends BaseRoleResourcePK {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public RoleResourcePK() {
	}

	public RoleResourcePK(Role roleid, Resources resourceid) {

		super(roleid, resourceid);
	}
	/* [CONSTRUCTOR MARKER END] */

}