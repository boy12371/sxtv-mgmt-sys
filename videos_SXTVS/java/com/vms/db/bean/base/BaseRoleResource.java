package com.vms.db.bean.base;

import java.io.Serializable;


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
	public static String PROP_ROLEID = "roleid";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_ID = "id";
	public static String PROP_RESOURCEID = "resourceid";


	// constructors
	public BaseRoleResource () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRoleResource (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseRoleResource (
		java.lang.Integer id,
		com.vms.db.bean.Role roleid,
		com.vms.db.bean.Resources resourceid,
		java.lang.String comments) {

		this.setId(id);
		this.setRoleid(roleid);
		this.setResourceid(resourceid);
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
	private com.vms.db.bean.Resources resourceid;
	private com.vms.db.bean.Role roleid;



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
	 * Return the value associated with the column: resourceid
	 */
	public com.vms.db.bean.Resources getResourceid () {
		return resourceid;
	}

	/**
	 * Set the value related to the column: resourceid
	 * @param resourceid the resourceid value
	 */
	public void setResourceid (com.vms.db.bean.Resources resourceid) {
		this.resourceid = resourceid;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.RoleResource)) return false;
		else {
			com.vms.db.bean.RoleResource roleResource = (com.vms.db.bean.RoleResource) obj;
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