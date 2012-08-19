package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="user"
 */

public abstract class BaseUser  implements Serializable {

	public static String REF = "User";
	public static String PROP_STATUS = "status";
	public static String PROP_ID = "id";
	public static String PROP_USER_NAME = "userName";
	public static String PROP_EMPLOYEE = "employee";
	public static String PROP_USER_PASS = "userPass";


	// constructors
	public BaseUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUser (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseUser (
		java.lang.Integer id,
		com.vms.db.bean.Employee employee,
		java.lang.String userName,
		java.lang.String userPass,
		java.lang.Integer status) {

		this.setId(id);
		this.setEmployee(employee);
		this.setUserName(userName);
		this.setUserPass(userPass);
		this.setStatus(status);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String userName;
	private java.lang.String userPass;
	private java.lang.Integer status;

	// many to one
	private com.vms.db.bean.Employee employee;

	// collections
	private java.util.Set<com.vms.db.bean.Role> roles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="userID"
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
	 * Return the value associated with the column: userName
	 */
	public java.lang.String getUserName () {
		return userName;
	}

	/**
	 * Set the value related to the column: userName
	 * @param userName the userName value
	 */
	public void setUserName (java.lang.String userName) {
		this.userName = userName;
	}



	/**
	 * Return the value associated with the column: userPass
	 */
	public java.lang.String getUserPass () {
		return userPass;
	}

	/**
	 * Set the value related to the column: userPass
	 * @param userPass the userPass value
	 */
	public void setUserPass (java.lang.String userPass) {
		this.userPass = userPass;
	}



	/**
	 * Return the value associated with the column: status
	 */
	public java.lang.Integer getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.Integer status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: employee
	 */
	public com.vms.db.bean.Employee getEmployee () {
		return employee;
	}

	/**
	 * Set the value related to the column: employee
	 * @param employee the employee value
	 */
	public void setEmployee (com.vms.db.bean.Employee employee) {
		this.employee = employee;
	}



	/**
	 * Return the value associated with the column: Roles
	 */
	public java.util.Set<com.vms.db.bean.Role> getRoles () {
		return roles;
	}

	/**
	 * Set the value related to the column: Roles
	 * @param roles the Roles value
	 */
	public void setRoles (java.util.Set<com.vms.db.bean.Role> roles) {
		this.roles = roles;
	}

	public void addToRoles (com.vms.db.bean.Role role) {
		if (null == getRoles()) setRoles(new java.util.TreeSet<com.vms.db.bean.Role>());
		getRoles().add(role);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.User)) return false;
		else {
			com.vms.db.bean.User user = (com.vms.db.bean.User) obj;
			if (null == this.getId() || null == user.getId()) return false;
			else return (this.getId().equals(user.getId()));
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