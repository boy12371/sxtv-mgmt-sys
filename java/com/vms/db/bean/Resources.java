package com.vms.db.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.vms.db.bean.base.BaseResources;

public class Resources extends BaseResources {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Resources() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Resources(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Resources(java.lang.Integer id, java.lang.String url, java.lang.String comments) {

		super(id, url, comments);
	}

//	public String getRoleAuthorities() {
//		// TODO Auto-generated method stub
//		List<String> roleAuthorities = new ArrayList<String>();
//		Iterator<Role> it = this.getRoles().iterator();
//		while (it.hasNext()) {
//			Role role = it.next();
//			roleAuthorities.add(role.getName());
//		}
//        return StringUtils.join(roleAuthorities, ",");
//
//	}

	/* [CONSTRUCTOR MARKER END] */

}