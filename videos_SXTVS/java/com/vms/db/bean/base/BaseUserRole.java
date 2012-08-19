package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the user_role table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="user_role"
 */

public abstract class BaseUserRole  implements Serializable {

	public static String REF = "UserRole";
	public static String PROP_USERID = "userid";
	public static String PROP_ROLEID = "roleid";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_ID = "id";


	// constructors
	public BaseUserRole () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUserRole (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseUserRole (
		java.lang.Integer id,
		com.vms.db.bean.Role roleid,
		com.vms.db.bean.User userid,
		java.lang.String comments) {

		this.setId(id);
		this.setRoleid(roleid);
		this.setUserid(userid);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String comments;

	// many to one
	private com.vms.db.bean.Role roleid;
	private com.vms.db.bean.User userid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: comments
	 */
	public java.lang.String getComments () {
		return comments;
	}

	/**
	 * Set the value related to the column: comments
	 * @param comments the comments value
	 */
	public void setComments (java.lang.String comments) {
		this.comments = comments;
	}



	/**
	 * Return the value associated with the column: roleid
	 */
	public com.vms.db.bean.Role getRoleid () {
		return roleid;
	}

	/**
	 * Set the value related to the column: roleid
	 * @param roleid the roleid value
	 */
	public void setRoleid (com.vms.db.bean.Role roleid) {
		this.roleid = roleid;
	}



	/**
	 * Return the value associated with the column: userid
	 */
	public com.vms.db.bean.User getUserid () {
		return userid;
	}

	/**
	 * Set the value related to the column: userid
	 * @param userid the userid value
	 */
	public void setUserid (com.vms.db.bean.User userid) {
		this.userid = userid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.UserRole)) return false;
		else {
			com.vms.db.bean.UserRole userRole = (com.vms.db.bean.UserRole) obj;
			if (null == this.getId() || null == userRole.getId()) return false;
			else return (this.getId().equals(userRole.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}