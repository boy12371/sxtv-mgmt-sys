package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Resources;
import com.vms.db.bean.Role;
import com.vms.db.bean.User;


/**
 * This is an object that contains data related to the role table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="role"
 */

public abstract class BaseRole  implements Serializable {

	public static String REF = "Role";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";


	// constructors
	public BaseRole () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRole (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseRole (
		java.lang.Integer id,
		java.lang.String name) {

		this.setId(id);
		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String comments;

	// collections
	
	private java.util.Set<com.vms.db.bean.Resources> resources;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
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
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
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
	 * Return the value associated with the column: resources
	 */
	public java.util.Set<com.vms.db.bean.Resources> getResources () {
		return resources;
	}

	/**
	 * Set the value related to the column: resources
	 * @param resources the resources value
	 */
	public void setResources (java.util.Set<com.vms.db.bean.Resources> resources) {
		this.resources = resources;
	}

	public void addToresources (com.vms.db.bean.Resources resources) {
		if (null == getResources()) setResources(new java.util.TreeSet<com.vms.db.bean.Resources>());
		getResources().add(resources);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Role)) return false;
		else {
			com.vms.db.bean.Role role = (com.vms.db.bean.Role) obj;
			if (null == this.getId() || null == role.getId()) return false;
			else return (this.getId().equals(role.getId()));
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