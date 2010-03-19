package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Subject;
import com.vms.db.bean.Vediotape;


/**
 * This is an object that contains data related to the subject table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="subject"
 */

public abstract class BaseSubject  implements Serializable {

	public static String REF = "Subject";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_SUBJECT_NAME = "subjectName";
	public static String PROP_STATUS = "status";
	public static String PROP_ID = "id";


	// constructors
	public BaseSubject () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSubject (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSubject (
		java.lang.Integer id,
		java.lang.String subjectName,java.lang.Integer status,
		java.lang.String comments) {

		this.setId(id);
		this.setSubjectName(subjectName);
		this.setStatus(status);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String subjectName;
	private java.lang.Integer status;
	private java.lang.String comments;

	// collections
	private java.util.Set<com.vms.db.bean.Vediotape> vediotapes;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="subjectID"
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
	 * Return the value associated with the column: subjectName
	 */
	public java.lang.String getSubjectName () {
		return subjectName;
	}

	/**
	 * Set the value related to the column: subjectName
	 * @param subjectName the subjectName value
	 */
	public void setSubjectName (java.lang.String subjectName) {
		this.subjectName = subjectName;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Subject)) return false;
		else {
			com.vms.db.bean.Subject subject = (com.vms.db.bean.Subject) obj;
			if (null == this.getId() || null == subject.getId()) return false;
			else return (this.getId().equals(subject.getId()));
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

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}


}
