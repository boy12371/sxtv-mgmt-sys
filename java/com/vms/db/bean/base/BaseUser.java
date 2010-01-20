package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Auditing;
import com.vms.db.bean.Employee;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
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
		java.lang.String userPass) {

		this.setId(id);
		this.setEmployee(employee);
		this.setUserName(userName);
		this.setUserPass(userPass);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String userName;
	private java.lang.String userPass;

	// many to one
	private com.vms.db.bean.Employee employee;

	// collections
	private java.util.Set<com.vms.db.bean.Vedioscore> vedioscores;
	private java.util.Set<com.vms.db.bean.Playorder> playorders;
	private java.util.Set<com.vms.db.bean.Playchangelog> playchangelogs;
	private java.util.Set<com.vms.db.bean.Vediotape> vediotapes;
	private java.util.Set<com.vms.db.bean.Auditing> auditings;



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
	 * Return the value associated with the column: vedioscores
	 */
	public java.util.Set<com.vms.db.bean.Vedioscore> getVedioscores () {
		return vedioscores;
	}

	/**
	 * Set the value related to the column: vedioscores
	 * @param vedioscores the vedioscores value
	 */
	public void setVedioscores (java.util.Set<com.vms.db.bean.Vedioscore> vedioscores) {
		this.vedioscores = vedioscores;
	}

	public void addTovedioscores (com.vms.db.bean.Vedioscore vedioscore) {
		if (null == getVedioscores()) setVedioscores(new java.util.TreeSet<com.vms.db.bean.Vedioscore>());
		getVedioscores().add(vedioscore);
	}



	/**
	 * Return the value associated with the column: playorders
	 */
	public java.util.Set<com.vms.db.bean.Playorder> getPlayorders () {
		return playorders;
	}

	/**
	 * Set the value related to the column: playorders
	 * @param playorders the playorders value
	 */
	public void setPlayorders (java.util.Set<com.vms.db.bean.Playorder> playorders) {
		this.playorders = playorders;
	}

	public void addToplayorders (com.vms.db.bean.Playorder playorder) {
		if (null == getPlayorders()) setPlayorders(new java.util.TreeSet<com.vms.db.bean.Playorder>());
		getPlayorders().add(playorder);
	}



	/**
	 * Return the value associated with the column: playchangelogs
	 */
	public java.util.Set<com.vms.db.bean.Playchangelog> getPlaychangelogs () {
		return playchangelogs;
	}

	/**
	 * Set the value related to the column: playchangelogs
	 * @param playchangelogs the playchangelogs value
	 */
	public void setPlaychangelogs (java.util.Set<com.vms.db.bean.Playchangelog> playchangelogs) {
		this.playchangelogs = playchangelogs;
	}

	public void addToplaychangelogs (com.vms.db.bean.Playchangelog playchangelog) {
		if (null == getPlaychangelogs()) setPlaychangelogs(new java.util.TreeSet<com.vms.db.bean.Playchangelog>());
		getPlaychangelogs().add(playchangelog);
	}



	/**
	 * Return the value associated with the column: vediotapes
	 */
	public java.util.Set<com.vms.db.bean.Vediotape> getVediotapes () {
		return vediotapes;
	}

	/**
	 * Set the value related to the column: vediotapes
	 * @param vediotapes the vediotapes value
	 */
	public void setVediotapes (java.util.Set<com.vms.db.bean.Vediotape> vediotapes) {
		this.vediotapes = vediotapes;
	}

	public void addTovediotapes (com.vms.db.bean.Vediotape vediotape) {
		if (null == getVediotapes()) setVediotapes(new java.util.TreeSet<com.vms.db.bean.Vediotape>());
		getVediotapes().add(vediotape);
	}



	/**
	 * Return the value associated with the column: auditings
	 */
	public java.util.Set<com.vms.db.bean.Auditing> getAuditings () {
		return auditings;
	}

	/**
	 * Set the value related to the column: auditings
	 * @param auditings the auditings value
	 */
	public void setAuditings (java.util.Set<com.vms.db.bean.Auditing> auditings) {
		this.auditings = auditings;
	}

	public void addToauditings (com.vms.db.bean.Auditing auditing) {
		if (null == getAuditings()) setAuditings(new java.util.TreeSet<com.vms.db.bean.Auditing>());
		getAuditings().add(auditing);
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