package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the playorder table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="playorder"
 */

public abstract class BasePlayorder  implements Serializable {

	public static String REF = "Playorder";
	public static String PROP_VEDIO = "vedioID";
	public static String PROP_AUDITOR = "auditor";
	public static String PROP_ID = "id";
	public static String PROP_PLAY_DATE = "playDate";
	public static String PROP_ARRANGE_DATE = "arrangeDate";


	// constructors
	public BasePlayorder () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePlayorder (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePlayorder (
		java.lang.Integer id,
		com.vms.db.bean.User auditor,
		com.vms.db.bean.Vediotape vedioID,
		java.util.Date playDate,
		java.util.Date arrangeDate) {

		this.setId(id);
		this.setAuditor(auditor);
		this.setVedioID(vedioID);
		this.setPlayDate(playDate);
		this.setArrangeDate(arrangeDate);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date playDate;
	private java.util.Date arrangeDate;

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
	 * Return the value associated with the column: playDate
	 */
	public java.util.Date getPlayDate () {
		return playDate;
	}

	/**
	 * Set the value related to the column: playDate
	 * @param playDate the playDate value
	 */
	public void setPlayDate (java.util.Date playDate) {
		this.playDate = playDate;
	}



	/**
	 * Return the value associated with the column: arrangeDate
	 */
	public java.util.Date getArrangeDate () {
		return arrangeDate;
	}

	/**
	 * Set the value related to the column: arrangeDate
	 * @param arrangeDate the arrangeDate value
	 */
	public void setArrangeDate (java.util.Date arrangeDate) {
		this.arrangeDate = arrangeDate;
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
		if (!(obj instanceof com.vms.db.bean.Playorder)) return false;
		else {
			com.vms.db.bean.Playorder playorder = (com.vms.db.bean.Playorder) obj;
			if (null == this.getId() || null == playorder.getId()) return false;
			else return (this.getId().equals(playorder.getId()));
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