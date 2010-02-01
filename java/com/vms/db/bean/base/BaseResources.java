package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Resources;
import com.vms.db.bean.Role;


/**
 * This is an object that contains data related to the resources table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="resources"
 */

public abstract class BaseResources  implements Serializable {

	public static String REF = "Resources";
	public static String PROP_COMMENTS = "Comments";
	public static String PROP_URL = "Url";
	public static String PROP_ID = "Id";


	// constructors
	public BaseResources () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseResources (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseResources (
		java.lang.Integer id,
		java.lang.String url,
		java.lang.String comments) {

		this.setId(id);
		this.setUrl(url);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String url;
	private java.lang.String comments;

	// collections
	private java.util.Set<Role> roles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
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
	 * Return the value associated with the column: url
	 */
	public java.lang.String getUrl () {
		return url;
	}

	/**
	 * Set the value related to the column: url
	 * @param url the url value
	 */
	public void setUrl (java.lang.String url) {
		this.url = url;
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
	 * Return the value associated with the column: Roles
	 */
	public java.util.Set<Role> getRoles () {
		return roles;
	}

	/**
	 * Set the value related to the column: Roles
	 * @param roles the Roles value
	 */
	public void setRoles (java.util.Set<Role> roles) {
		this.roles = roles;
	}

	public void addToRoles (Role role) {
		if (null == getRoles()) setRoles(new java.util.TreeSet<Role>());
		getRoles().add(role);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Resources)) return false;
		else {
			Resources resources = (Resources) obj;
			if (null == this.getId() || null == resources.getId()) return false;
			else return (this.getId().equals(resources.getId()));
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