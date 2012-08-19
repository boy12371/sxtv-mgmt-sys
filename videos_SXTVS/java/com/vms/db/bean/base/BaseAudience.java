package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the audience table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="audience"
 */

public abstract class BaseAudience  implements Serializable {

	public static String REF = "Audience";
	public static String PROP_NAME = "name";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_AGE = "age";
	public static String PROP_CAREER = "career";
	public static String PROP_GENDER = "gender";
	public static String PROP_ID = "id";


	// constructors
	public BaseAudience () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAudience (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAudience (
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
	private java.lang.Integer age;
	private java.lang.Integer gender;
	private java.lang.String career;
	private java.lang.String comments;



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
	 * Return the value associated with the column: age
	 */
	public java.lang.Integer getAge () {
		return age;
	}

	/**
	 * Set the value related to the column: age
	 * @param age the age value
	 */
	public void setAge (java.lang.Integer age) {
		this.age = age;
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
	 * Return the value associated with the column: career
	 */
	public java.lang.String getCareer () {
		return career;
	}

	/**
	 * Set the value related to the column: career
	 * @param career the career value
	 */
	public void setCareer (java.lang.String career) {
		this.career = career;
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
		if (!(obj instanceof com.vms.db.bean.Audience)) return false;
		else {
			com.vms.db.bean.Audience audience = (com.vms.db.bean.Audience) obj;
			if (null == this.getId() || null == audience.getId()) return false;
			else return (this.getId().equals(audience.getId()));
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