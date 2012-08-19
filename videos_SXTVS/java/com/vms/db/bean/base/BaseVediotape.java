package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Company;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;

/**
 * This is an object that contains data related to the vediotape table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="vediotape"
 */

public abstract class BaseVediotape implements Serializable {

	public static String REF = "Vediotape";
	public static String PROP_STATUS = "status";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_COMPANY_ID = "companyID";
	public static String PROP_SUBJECT = "subject";
	public static String PROP_DATE_COMING = "dateComing";
	public static String PROP_INPUTER = "inputer";
	public static String PROP_TOPIC = "topic";
	public static String PROP_MARKET_SHARE = "marketShare";
	public static String PROP_AUDIENCE_RATING = "audienceRating";
	public static String PROP_VEDIO_NAME = "vedioName";
	public static String PROP_ID = "id";
	public static String PROP_DATE_INPUT = "dateInput";
	public static String PROP_AUDIENCESCORE ="audiencescore";
	
	public static String PROP_DATE_STORE ="dateStore";

	// constructors
	public BaseVediotape() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseVediotape(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseVediotape(java.lang.String id, com.vms.db.bean.Topic topic, com.vms.db.bean.User inputer,
			com.vms.db.bean.Status status, com.vms.db.bean.Subject subject, com.vms.db.bean.Company companyID,
			java.lang.String vedioName, java.util.Date dateComing, java.util.Date dateInput,java.util.Date dateStore) {

		this.setId(id);
		this.setTopic(topic);
		this.setInputer(inputer);
		this.setStatus(status);
		this.setSubject(subject);
		this.setCompanyID(companyID);
		this.setVedioName(vedioName);
		this.setDateComing(dateComing);
		this.setDateInput(dateInput);
		this.setDateStore(dateStore);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String vedioName;
	private java.util.Date dateComing;
	private java.util.Date dateInput;
	private java.util.Date dateStore;
	private java.lang.String comments;
	private java.lang.Float marketShare;
	private java.lang.Float audienceRating;

	// many to one
	private com.vms.db.bean.Topic topic;
	private com.vms.db.bean.User inputer;
	private com.vms.db.bean.Status status;
	private com.vms.db.bean.Subject subject;
	private com.vms.db.bean.Company companyID;

	// collections
	private java.util.Set<com.vms.db.bean.Vedioscore> vedioscores;
	private java.util.Set<com.vms.db.bean.Playorder> playorders;
	private java.util.Set<com.vms.db.bean.Playchangelog> playchangelogs;
	private java.util.Set<com.vms.db.bean.Audiencescore> audiencescore;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="assigned" column="vedioID"
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: vedioName
	 */
	public java.lang.String getVedioName() {
		return vedioName;
	}

	/**
	 * Set the value related to the column: vedioName
	 * 
	 * @param vedioName
	 *            the vedioName value
	 */
	public void setVedioName(java.lang.String vedioName) {
		this.vedioName = vedioName;
	}

	/**
	 * Return the value associated with the column: dateComing
	 */
	public java.util.Date getDateComing() {
		return dateComing;
	}

	/**
	 * Set the value related to the column: dateComing
	 * 
	 * @param dateComing
	 *            the dateComing value
	 */
	public void setDateComing(java.util.Date dateComing) {
		this.dateComing = dateComing;
	}

	/**
	 * Return the value associated with the column: dateInput
	 */
	public java.util.Date getDateInput() {
		return dateInput;
	}

	/**
	 * Set the value related to the column: dateInput
	 * 
	 * @param dateInput
	 *            the dateInput value
	 */
	public void setDateInput(java.util.Date dateInput) {
		this.dateInput = dateInput;
	}

	/**
	 * Return the value associated with the column: comments
	 */
	public java.lang.String getComments() {
		return comments;
	}

	/**
	 * Set the value related to the column: comments
	 * 
	 * @param comments
	 *            the comments value
	 */
	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	/**
	 * Return the value associated with the column: marketShare
	 */
	public java.lang.Float getMarketShare() {
		return marketShare;
	}

	/**
	 * Set the value related to the column: marketShare
	 * 
	 * @param marketShare
	 *            the marketShare value
	 */
	public void setMarketShare(java.lang.Float marketShare) {
		this.marketShare = marketShare;
	}

	/**
	 * Return the value associated with the column: audienceRating
	 */
	public java.lang.Float getAudienceRating() {
		return audienceRating;
	}

	/**
	 * Set the value related to the column: audienceRating
	 * 
	 * @param audienceRating
	 *            the audienceRating value
	 */
	public void setAudienceRating(java.lang.Float audienceRating) {
		this.audienceRating = audienceRating;
	}

	/**
	 * Return the value associated with the column: topic
	 */
	public com.vms.db.bean.Topic getTopic() {
		return topic;
	}

	/**
	 * Set the value related to the column: topic
	 * 
	 * @param topic
	 *            the topic value
	 */
	public void setTopic(com.vms.db.bean.Topic topic) {
		this.topic = topic;
	}

	/**
	 * Return the value associated with the column: inputer
	 */
	public com.vms.db.bean.User getInputer() {
		return inputer;
	}

	/**
	 * Set the value related to the column: inputer
	 * 
	 * @param inputer
	 *            the inputer value
	 */
	public void setInputer(com.vms.db.bean.User inputer) {
		this.inputer = inputer;
	}

	/**
	 * Return the value associated with the column: status
	 */
	public com.vms.db.bean.Status getStatus() {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * 
	 * @param status
	 *            the status value
	 */
	public void setStatus(com.vms.db.bean.Status status) {
		this.status = status;
	}

	/**
	 * Return the value associated with the column: subject
	 */
	public com.vms.db.bean.Subject getSubject() {
		return subject;
	}

	/**
	 * Set the value related to the column: subject
	 * 
	 * @param subject
	 *            the subject value
	 */
	public void setSubject(com.vms.db.bean.Subject subject) {
		this.subject = subject;
	}

	/**
	 * Return the value associated with the column: companyID
	 */
	public com.vms.db.bean.Company getCompanyID() {
		return companyID;
	}

	/**
	 * Set the value related to the column: companyID
	 * 
	 * @param companyID
	 *            the companyID value
	 */
	public void setCompanyID(com.vms.db.bean.Company companyID) {
		this.companyID = companyID;
	}

	/**
	 * Return the value associated with the column: vedioscores
	 */
	public java.util.Set<com.vms.db.bean.Vedioscore> getVedioscores() {
		return vedioscores;
	}

	/**
	 * Set the value related to the column: vedioscores
	 * 
	 * @param vedioscores
	 *            the vedioscores value
	 */
	public void setVedioscores(java.util.Set<com.vms.db.bean.Vedioscore> vedioscores) {
		this.vedioscores = vedioscores;
	}

	public void addTovedioscores(com.vms.db.bean.Vedioscore vedioscore) {
		if (null == getVedioscores())
			setVedioscores(new java.util.TreeSet<com.vms.db.bean.Vedioscore>());
		getVedioscores().add(vedioscore);
	}

	/**
	 * Return the value associated with the column: playorders
	 */
	public java.util.Set<com.vms.db.bean.Playorder> getPlayorders() {
		return playorders;
	}

	/**
	 * Set the value related to the column: playorders
	 * 
	 * @param playorders
	 *            the playorders value
	 */
	public void setPlayorders(java.util.Set<com.vms.db.bean.Playorder> playorders) {
		this.playorders = playorders;
	}

	public void addToplayorders(com.vms.db.bean.Playorder playorder) {
		if (null == getPlayorders())
			setPlayorders(new java.util.TreeSet<com.vms.db.bean.Playorder>());
		getPlayorders().add(playorder);
	}

	/**
	 * Return the value associated with the column: playchangelogs
	 */
	public java.util.Set<com.vms.db.bean.Playchangelog> getPlaychangelogs() {
		return playchangelogs;
	}

	/**
	 * Set the value related to the column: playchangelogs
	 * 
	 * @param playchangelogs
	 *            the playchangelogs value
	 */
	public void setPlaychangelogs(java.util.Set<com.vms.db.bean.Playchangelog> playchangelogs) {
		this.playchangelogs = playchangelogs;
	}

	public void addToplaychangelogs(com.vms.db.bean.Playchangelog playchangelog) {
		if (null == getPlaychangelogs())
			setPlaychangelogs(new java.util.TreeSet<com.vms.db.bean.Playchangelog>());
		getPlaychangelogs().add(playchangelog);
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.vms.db.bean.Vediotape))
			return false;
		else {
			com.vms.db.bean.Vediotape vediotape = (com.vms.db.bean.Vediotape) obj;
			if (null == this.getId() || null == vediotape.getId())
				return false;
			else
				return (this.getId().equals(vediotape.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

	public java.util.Set<com.vms.db.bean.Audiencescore> getAudiencescore() {
		return audiencescore;
	}

	public void setAudiencescore(
			java.util.Set<com.vms.db.bean.Audiencescore> audiencescore) {
		this.audiencescore = audiencescore;
	}

	
	public java.util.Date getDateStore() {
		return dateStore;
	}

	
	public void setDateStore(java.util.Date dateStore) {
		this.dateStore = dateStore;
	}

}