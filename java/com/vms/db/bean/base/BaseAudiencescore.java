package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the audiencescore table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="audiencescore"
 */

public abstract class BaseAudiencescore  implements Serializable {

	public static String REF = "Audiencescore";
	public static String PROP_VEDIO_I_D = "vedioID";
	public static String PROP_RESULT = "result";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_DATE_EXAMINE = "dateExamine";
	public static String PROP_AUDIENCE_I_D = "audienceID";


	// constructors
	public BaseAudiencescore () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAudiencescore (
		com.vms.db.bean.Vediotape vedioID,
		com.vms.db.bean.Audience audienceID,
		java.util.Date dateExamine,
		java.lang.Integer result) {

		this.setVedioID(vedioID);
		this.setAudienceID(audienceID);
		this.setAudienceID(audienceID);
		this.setDateExamine(dateExamine);
		this.setResult(result);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.util.Date dateExamine;
	private java.lang.Integer result;
	private java.lang.String comments;

	// many to one
	private com.vms.db.bean.Vediotape vedioID;
	private com.vms.db.bean.Audience audienceID;



	/**
	 * Return the value associated with the column: dateExamine
	 */
	public java.util.Date getDateExamine () {
		return dateExamine;
	}

	/**
	 * Set the value related to the column: dateExamine
	 * @param dateExamine the dateExamine value
	 */
	public void setDateExamine (java.util.Date dateExamine) {
		this.dateExamine = dateExamine;
	}



	/**
	 * Return the value associated with the column: result
	 */
	public java.lang.Integer getResult () {
		return result;
	}

	/**
	 * Set the value related to the column: result
	 * @param result the result value
	 */
	public void setResult (java.lang.Integer result) {
		this.result = result;
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



	/**
	 * Return the value associated with the column: audienceID
	 */
	public com.vms.db.bean.Audience getAudienceID () {
		return audienceID;
	}

	/**
	 * Set the value related to the column: audienceID
	 * @param audienceID the audienceID value
	 */
	public void setAudienceID (com.vms.db.bean.Audience audienceID) {
		this.audienceID = audienceID;
	}







	public String toString () {
		return super.toString();
	}


}