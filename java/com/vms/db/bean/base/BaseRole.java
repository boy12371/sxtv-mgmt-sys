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
	public static String PROP_NAME = "Name";
	public static String PROP_COMMENTS = "Comments";
	public static String PROP_ID = "Id";


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
		java.lang.String name,
		java.lang.String comments) {

		this.setId(id);
		this.setName(name);
		this.setComments(comments);
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
	private java.util.Set<Resources> resources;
	private java.util.Set<User> users;



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
	public void setId (int id) {
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
	 * Return the value associated with the column: Resources
	 */
	public java.util.Set<Resources> getResources () {
		return resources;
	}

	/**
	 * Set the value related to the column: Resources
	 * @param resources the Resources value
	 */
	public void setResources (java.util.Set<Resources> resources) {
		this.resources = resources;
	}

	public void addToResources (Resources resources) {
		if (null == getResources()) setResources(new java.util.TreeSet<Resources>());
		getResources().add(resources);
	}



	/**
	 * Return the value associated with the column: Users
	 */
	public java.util.Set<User> getUsers () {
		return users;
	}

	/**
	 * Set the value related to the column: Users
	 * @param users the Users value
	 */
	public void setUsers (java.util.Set<User> users) {
		this.users = users;
	}

	public void addToUsers (User user) {
		if (null == getUsers()) setUsers(new java.util.TreeSet<User>());
		getUsers().add(user);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Role)) return false;
		else {
			Role role = (Role) obj;
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