package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Resources;
import com.vms.db.bean.Role;
import com.vms.db.bean.RoleResourcePK;


public abstract class BaseRoleResourcePK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private Role roleid;
	private Resources resourceid;


	public BaseRoleResourcePK () {}
	
	public BaseRoleResourcePK (
		Role roleid,
		Resources resourceid) {

		this.setRoleid(roleid);
		this.setResourceid(resourceid);
	}


	/**
	 * Return the value associated with the column: roleid
	 */
	public Role getRoleid () {
		return roleid;
	}

	/**
	 * Set the value related to the column: roleid
	 * @param roleid the roleid value
	 */
	public void setRoleid (Role roleid) {
		this.roleid = roleid;
	}



	/**
	 * Return the value associated with the column: resourceid
	 */
	public Resources getResourceid () {
		return resourceid;
	}

	/**
	 * Set the value related to the column: resourceid
	 * @param resourceid the resourceid value
	 */
	public void setResourceid (Resources resourceid) {
		this.resourceid = resourceid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RoleResourcePK)) return false;
		else {
			RoleResourcePK mObj = (RoleResourcePK) obj;
			if (null != this.getRoleid() && null != mObj.getRoleid()) {
				if (!this.getRoleid().equals(mObj.getRoleid())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getResourceid() && null != mObj.getResourceid()) {
				if (!this.getResourceid().equals(mObj.getResourceid())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getRoleid()) {
				sb.append(this.getRoleid().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getResourceid()) {
				sb.append(this.getResourceid().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}