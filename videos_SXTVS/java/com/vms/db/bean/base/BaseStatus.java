package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Auditing;
import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;


/**
 * This is an object that contains data related to the status table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="status"
 */

public abstract class BaseStatus  implements Serializable {

	public static String REF = "Status";
	public static String PROP_STATUS = "status";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_ID = "id";


	// constructors
	public BaseStatus () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseStatus (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseStatus (
		java.lang.Integer id,
		java.lang.String status,
		java.lang.String comments) {

		this.setId(id);
		this.setStatus(status);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String status;
	private java.lang.String comments;

	// collections
	private java.util.Set<com.vms.db.bean.Vediotape> vediotapes;
	private java.util.Set<com.vms.db.bean.Auditing> auditings;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="statusID"
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
	 * Return the value associated with the column: status
	 */
	public java.lang.String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.String status) {
		this.status = status;
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
		if (!(obj instanceof com.vms.db.bean.Status)) return false;
		else {
			com.vms.db.bean.Status status = (com.vms.db.bean.Status) obj;
			if (null == this.getId() || null == status.getId()) return false;
			else return (this.getId().equals(status.getId()));
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