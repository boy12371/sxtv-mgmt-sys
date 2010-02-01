package com.vms.db.bean.base;
import java.io.Serializable;

import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.bean.UserRolePK;


public abstract class BaseUserRolePK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private Role roleid;
	private User userid;


	public BaseUserRolePK () {}
	
	public BaseUserRolePK (
		Role roleid,
		User userid) {

		this.setRoleid(roleid);
		this.setUserid(userid);
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
	 * Return the value associated with the column: userid
	 */
	public User getUserid () {
		return userid;
	}

	/**
	 * Set the value related to the column: userid
	 * @param userid the userid value
	 */
	public void setUserid (User userid) {
		this.userid = userid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof UserRolePK)) return false;
		else {
			UserRolePK mObj = (UserRolePK) obj;
			if (null != this.getRoleid() && null != mObj.getRoleid()) {
				if (!this.getRoleid().equals(mObj.getRoleid())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getUserid() && null != mObj.getUserid()) {
				if (!this.getUserid().equals(mObj.getUserid())) {
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
			if (null != this.getUserid()) {
				sb.append(this.getUserid().hashCode());
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