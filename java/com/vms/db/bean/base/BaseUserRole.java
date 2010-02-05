package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.UserRole;
import com.vms.db.bean.UserRolePK;

/**
 * This is an object that contains data related to the user_role table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="user_role"
 */

public abstract class BaseUserRole implements Serializable {

	public static String REF = "UserRole";
	public static String PROP_COMMENTS = "Comments";
	public static String PROP_ID = "Id";

	// constructors
	public BaseUserRole() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUserRole(UserRolePK id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseUserRole(UserRolePK id, java.lang.String comments) {

		this.setId(id);
		this.setComments(comments);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private UserRolePK id;

	// fields
	private java.lang.String comments;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id
	 */
	public UserRolePK getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(UserRolePK id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: comments
	 */
	public java.lang.String getComments() {
		return comments;
	}

	/**
	 * Set the value related to the column: comments
	 * 
	 * @param comments
	 *            the comments value
	 */
	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof UserRole))
			return false;
		else {
			UserRole userRole = (UserRole) obj;
			if (null == this.getId() || null == userRole.getId())
				return false;
			else
				return (this.getId().equals(userRole.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}