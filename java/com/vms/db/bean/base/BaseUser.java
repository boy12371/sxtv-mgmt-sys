package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Auditing;
import com.vms.db.bean.Employee;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;


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
	public static String PROP_STATUS = "Status";
	public static String PROP_ID = "id";
	public static String PROP_USER_NAME = "UserName";
	public static String PROP_EMPLOYEE = "Employee";
	public static String PROP_USER_PASS = "UserPass";


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
		Employee employee,
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
	private Employee employee;

	// collections
	private java.util.Set<Vedioscore> vedioscores;
	private java.util.Set<Playchangelog> playchangelogs;
	private java.util.Set<Vediotape> vediotapes;
	private java.util.Set<Playorder> playorders;
	private java.util.Set<Auditing> auditings;
	private java.util.Set<Role> roles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
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
	public Employee getEmployee () {
		return employee;
	}

	/**
	 * Set the value related to the column: employee
	 * @param employee the employee value
	 */
	public void setEmployee (Employee employee) {
		this.employee = employee;
	}



	/**
	 * Return the value associated with the column: Vedioscores
	 */
	public java.util.Set<Vedioscore> getVedioscores () {
		return vedioscores;
	}

	/**
	 * Set the value related to the column: Vedioscores
	 * @param vedioscores the Vedioscores value
	 */
	public void setVedioscores (java.util.Set<Vedioscore> vedioscores) {
		this.vedioscores = vedioscores;
	}

	public void addToVedioscores (Vedioscore vedioscore) {
		if (null == getVedioscores()) setVedioscores(new java.util.TreeSet<Vedioscore>());
		getVedioscores().add(vedioscore);
	}



	/**
	 * Return the value associated with the column: Playchangelogs
	 */
	public java.util.Set<Playchangelog> getPlaychangelogs () {
		return playchangelogs;
	}

	/**
	 * Set the value related to the column: Playchangelogs
	 * @param playchangelogs the Playchangelogs value
	 */
	public void setPlaychangelogs (java.util.Set<Playchangelog> playchangelogs) {
		this.playchangelogs = playchangelogs;
	}

	public void addToPlaychangelogs (Playchangelog playchangelog) {
		if (null == getPlaychangelogs()) setPlaychangelogs(new java.util.TreeSet<Playchangelog>());
		getPlaychangelogs().add(playchangelog);
	}



	/**
	 * Return the value associated with the column: Vediotapes
	 */
	public java.util.Set<Vediotape> getVediotapes () {
		return vediotapes;
	}

	/**
	 * Set the value related to the column: Vediotapes
	 * @param vediotapes the Vediotapes value
	 */
	public void setVediotapes (java.util.Set<Vediotape> vediotapes) {
		this.vediotapes = vediotapes;
	}

	public void addToVediotapes (Vediotape vediotape) {
		if (null == getVediotapes()) setVediotapes(new java.util.TreeSet<Vediotape>());
		getVediotapes().add(vediotape);
	}



	/**
	 * Return the value associated with the column: Playorders
	 */
	public java.util.Set<Playorder> getPlayorders () {
		return playorders;
	}

	/**
	 * Set the value related to the column: Playorders
	 * @param playorders the Playorders value
	 */
	public void setPlayorders (java.util.Set<Playorder> playorders) {
		this.playorders = playorders;
	}

	public void addToPlayorders (Playorder playorder) {
		if (null == getPlayorders()) setPlayorders(new java.util.TreeSet<Playorder>());
		getPlayorders().add(playorder);
	}



	/**
	 * Return the value associated with the column: Auditings
	 */
	public java.util.Set<Auditing> getAuditings () {
		return auditings;
	}

	/**
	 * Set the value related to the column: Auditings
	 * @param auditings the Auditings value
	 */
	public void setAuditings (java.util.Set<Auditing> auditings) {
		this.auditings = auditings;
	}

	public void addToAuditings (Auditing auditing) {
		if (null == getAuditings()) setAuditings(new java.util.TreeSet<Auditing>());
		getAuditings().add(auditing);
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
		if (!(obj instanceof User)) return false;
		else {
			User user = (User) obj;
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