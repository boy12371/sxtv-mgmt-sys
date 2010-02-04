package com.vms.db.bean;

import java.io.Serializable;

import com.vms.db.bean.base.BaseRole;

public class Role implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Role";
	public static String PROP_NAME = "Name";
	public static String PROP_COMMENTS = "Comments";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String comments;

	// collections
	private java.util.Set<Resources> resources;
	private java.util.Set<User> users;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	/**
	 * Return the value associated with the column: Resources
	 */
	public java.util.Set<Resources> getResources() {
		return resources;
	}

	/**
	 * Set the value related to the column: Resources
	 * 
	 * @param resources
	 *            the Resources value
	 */
	public void setResources(java.util.Set<Resources> resources) {
		this.resources = resources;
	}

	public void addToResources(Resources resources) {
		if (null == getResources())
			setResources(new java.util.TreeSet<Resources>());
		getResources().add(resources);
	}

	/**
	 * Return the value associated with the column: Users
	 */
	public java.util.Set<User> getUsers() {
		return users;
	}

	/**
	 * Set the value related to the column: Users
	 * 
	 * @param users
	 *            the Users value
	 */
	public void setUsers(java.util.Set<User> users) {
		this.users = users;
	}

	public void addToUsers(User user) {
		if (null == getUsers())
			setUsers(new java.util.TreeSet<User>());
		getUsers().add(user);
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Role))
			return false;
		else {
			Role role = (Role) obj;
			if (null == this.getId() || null == role.getId())
				return false;
			else
				return (this.getId().equals(role.getId()));
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