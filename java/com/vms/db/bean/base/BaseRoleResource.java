package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.RoleResource;
import com.vms.db.bean.RoleResourcePK;


/**
 * This is an object that contains data related to the role_resource table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="role_resource"
 */

public abstract class BaseRoleResource  implements Serializable {

	public static String REF = "RoleResource";
	public static String PROP_COMMENTS = "Comments";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRoleResource () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRoleResource (RoleResourcePK id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseRoleResource (
		RoleResourcePK id,
		java.lang.String comments) {

		this.setId(id);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private RoleResourcePK id;

	// fields
	private java.lang.String comments;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public RoleResourcePK getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (RoleResourcePK id) {
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RoleResource)) return false;
		else {
			RoleResource roleResource = (RoleResource) obj;
			if (null == this.getId() || null == roleResource.getId()) return false;
			else return (this.getId().equals(roleResource.getId()));
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