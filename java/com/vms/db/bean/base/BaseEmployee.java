package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Employee;
import com.vms.db.bean.User;


/**
 * This is an object that contains data related to the employee table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="employee"
 */

public abstract class BaseEmployee  implements Serializable {

	public static String REF = "Employee";
	public static String PROP_NAME = "name";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_BIRTHDAY = "birthday";
	public static String PROP_CONTRACT_DATE = "contractDate";
	public static String PROP_GENDER = "gender";
	public static String PROP_ID = "id";
	public static String PROP_TEL = "tel";


	// constructors
	public BaseEmployee () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseEmployee (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseEmployee (
		java.lang.Integer id,
		java.lang.String name,
		java.util.Date birthday,
		java.util.Date contractDate,
		java.lang.Integer gender,
		java.lang.String tel,
		java.lang.String comments) {

		this.setId(id);
		this.setName(name);
		this.setBirthday(birthday);
		this.setContractDate(contractDate);
		this.setGender(gender);
		this.setTel(tel);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.util.Date birthday;
	private java.util.Date contractDate;
	private java.lang.Integer gender;
	private java.lang.String tel;
	private java.lang.String comments;

	// collections
	private java.util.Set<com.vms.db.bean.User> users;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="employeeID"
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
	 * Return the value associated with the column: birthday
	 */
	public java.util.Date getBirthday () {
		return birthday;
	}

	/**
	 * Set the value related to the column: birthday
	 * @param birthday the birthday value
	 */
	public void setBirthday (java.util.Date birthday) {
		this.birthday = birthday;
	}



	/**
	 * Return the value associated with the column: contractDate
	 */
	public java.util.Date getContractDate () {
		return contractDate;
	}

	/**
	 * Set the value related to the column: contractDate
	 * @param contractDate the contractDate value
	 */
	public void setContractDate (java.util.Date contractDate) {
		this.contractDate = contractDate;
	}



	/**
	 * Return the value associated with the column: gender
	 */
	public java.lang.Integer getGender () {
		return gender;
	}

	/**
	 * Set the value related to the column: gender
	 * @param gender the gender value
	 */
	public void setGender (java.lang.Integer gender) {
		this.gender = gender;
	}



	/**
	 * Return the value associated with the column: tel
	 */
	public java.lang.String getTel () {
		return tel;
	}

	/**
	 * Set the value related to the column: tel
	 * @param tel the tel value
	 */
	public void setTel (java.lang.String tel) {
		this.tel = tel;
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
	 * Return the value associated with the column: users
	 */
	public java.util.Set<com.vms.db.bean.User> getUsers () {
		return users;
	}

	/**
	 * Set the value related to the column: users
	 * @param users the users value
	 */
	public void setUsers (java.util.Set<com.vms.db.bean.User> users) {
		this.users = users;
	}

	public void addTousers (com.vms.db.bean.User user) {
		if (null == getUsers()) setUsers(new java.util.TreeSet<com.vms.db.bean.User>());
		getUsers().add(user);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Employee)) return false;
		else {
			com.vms.db.bean.Employee employee = (com.vms.db.bean.Employee) obj;
			if (null == this.getId() || null == employee.getId()) return false;
			else return (this.getId().equals(employee.getId()));
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