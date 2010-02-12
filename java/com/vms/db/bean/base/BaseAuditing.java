package com.vms.db.bean.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the auditing table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="auditing"
 */

public abstract class BaseAuditing implements
		Serializable {
	public static String REF = "Auditing";
	public static String PROP_VEDIO_I_D = "vedioID";
	public static String PROP_RESULT = "result";
	public static String PROP_AUDITOR = "auditor";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_ID = "id";
	public static String PROP_AUDIT_DATE = "auditDate";


	// constructors
	public BaseAuditing () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAuditing (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAuditing (
		java.lang.Integer id,
		com.vms.db.bean.User auditor,
		com.vms.db.bean.Status result,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date auditDate) {

		this.setId(id);
		this.setAuditor(auditor);
		this.setResult(result);
		this.setVedioID(vedioID);
		this.setAuditDate(auditDate);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date auditDate;
	private java.lang.String comments;

	// many to one
	private com.vms.db.bean.User auditor;
	private com.vms.db.bean.Status result;
	private com.vms.db.bean.Vediotape vedioID;



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
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: auditDate
	 */
	public java.util.Date getAuditDate () {
		return auditDate;
	}

	/**
	 * Set the value related to the column: auditDate
	 * @param auditDate the auditDate value
	 */
	public void setAuditDate (java.util.Date auditDate) {
		this.auditDate = auditDate;
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
	 * Return the value associated with the column: auditor
	 */
	public com.vms.db.bean.User getAuditor () {
		return auditor;
	}

	/**
	 * Set the value related to the column: auditor
	 * @param auditor the auditor value
	 */
	public void setAuditor (com.vms.db.bean.User auditor) {
		this.auditor = auditor;
	}



	/**
	 * Return the value associated with the column: result
	 */
	public com.vms.db.bean.Status getResult () {
		return result;
	}

	/**
	 * Set the value related to the column: result
	 * @param result the result value
	 */
	public void setResult (com.vms.db.bean.Status result) {
		this.result = result;
	}



	/**
	 * Return the value associated with the column: vedioID
	 */
	public com.vms.db.bean.Vediotape getVedioID () {
		return vedioID;
	}

	/**
	 * Set the value related to the column: vedioID
	 * @param vedioID the vedioID value
	 */
	public void setVedioID (com.vms.db.bean.Vediotape vedioID) {
		this.vedioID = vedioID;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Auditing)) return false;
		else {
			com.vms.db.bean.Auditing auditing = (com.vms.db.bean.Auditing) obj;
			if (null == this.getId() || null == auditing.getId()) return false;
			else return (this.getId().equals(auditing.getId()));
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