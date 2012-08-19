package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the playchangelog table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="playchangelog"
 */

public abstract class BasePlaychangelog  implements Serializable {

	public static String REF = "Playchangelog";
	public static String PROP_VEDIO_I_D = "vedioID";
	public static String PROP_AUDITOR = "auditor";
	public static String PROP_OPERATION = "operation";
	public static String PROP_TO_DATE = "toDate";
	public static String PROP_DATE = "date";
	public static String PROP_FROM_DATE = "fromDate";
	public static String PROP_ID = "id";


	// constructors
	public BasePlaychangelog () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePlaychangelog (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePlaychangelog (
		java.lang.Integer id,
		com.vms.db.bean.User auditor,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date date) {

		this.setId(id);
		this.setAuditor(auditor);
		this.setVedioID(vedioID);
		this.setDate(date);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date fromDate;
	private java.util.Date toDate;
	private java.lang.String operation;
	private java.util.Date date;

	// many to one
	private com.vms.db.bean.User auditor;
	private com.vms.db.bean.Vediotape vedioID;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="Id"
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
	 * Return the value associated with the column: fromDate
	 */
	public java.util.Date getFromDate () {
		return fromDate;
	}

	/**
	 * Set the value related to the column: fromDate
	 * @param fromDate the fromDate value
	 */
	public void setFromDate (java.util.Date fromDate) {
		this.fromDate = fromDate;
	}



	/**
	 * Return the value associated with the column: toDate
	 */
	public java.util.Date getToDate () {
		return toDate;
	}

	/**
	 * Set the value related to the column: toDate
	 * @param toDate the toDate value
	 */
	public void setToDate (java.util.Date toDate) {
		this.toDate = toDate;
	}



	/**
	 * Return the value associated with the column: operation
	 */
	public java.lang.String getOperation () {
		return operation;
	}

	/**
	 * Set the value related to the column: operation
	 * @param operation the operation value
	 */
	public void setOperation (java.lang.String operation) {
		this.operation = operation;
	}



	/**
	 * Return the value associated with the column: date
	 */
	public java.util.Date getDate () {
		return date;
	}

	/**
	 * Set the value related to the column: date
	 * @param date the date value
	 */
	public void setDate (java.util.Date date) {
		this.date = date;
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
		if (!(obj instanceof com.vms.db.bean.Playchangelog)) return false;
		else {
			com.vms.db.bean.Playchangelog playchangelog = (com.vms.db.bean.Playchangelog) obj;
			if (null == this.getId() || null == playchangelog.getId()) return false;
			else return (this.getId().equals(playchangelog.getId()));
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