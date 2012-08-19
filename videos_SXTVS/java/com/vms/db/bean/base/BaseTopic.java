package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Topic;
import com.vms.db.bean.Vediotape;

/**
 * This is an object that contains data related to the topic table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="topic"
 */

public abstract class BaseTopic implements Serializable {

	public static String REF = "Topic";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_ID = "id";
	public static String PROP_TOPIC_NAME = "topicName";
	public static String PROP_STATUS = "status";

	// constructors
	public BaseTopic() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTopic(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTopic(java.lang.Integer id, java.lang.String topicName,java.lang.Integer status,
			java.lang.String comments) {

		this.setId(id);
		this.setTopicName(topicName);
		this.setStatus(status);
		this.setComments(comments);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String topicName;
	private java.lang.Integer status;
	private java.lang.String comments;

	// collections
	private java.util.Set<com.vms.db.bean.Vediotape> vediotapes;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="sequence" column="topicID"
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: topicName
	 */
	public java.lang.String getTopicName() {
		return topicName;
	}

	/**
	 * Set the value related to the column: topicName
	 * 
	 * @param topicName
	 *            the topicName value
	 */
	public void setTopicName(java.lang.String topicName) {
		this.topicName = topicName;
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
	 * Return the value associated with the column: vediotapes
	 */
	public java.util.Set<com.vms.db.bean.Vediotape> getVediotapes() {
		return vediotapes;
	}

	/**
	 * Set the value related to the column: vediotapes
	 * 
	 * @param vediotapes
	 *            the vediotapes value
	 */
	public void setVediotapes(
			java.util.Set<com.vms.db.bean.Vediotape> vediotapes) {
		this.vediotapes = vediotapes;
	}

	public void addTovediotapes(com.vms.db.bean.Vediotape vediotape) {
		if (null == getVediotapes())
			setVediotapes(new java.util.TreeSet<com.vms.db.bean.Vediotape>());
		getVediotapes().add(vediotape);
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.vms.db.bean.Topic))
			return false;
		else {
			com.vms.db.bean.Topic topic = (com.vms.db.bean.Topic) obj;
			if (null == this.getId() || null == topic.getId())
				return false;
			else
				return (this.getId().equals(topic.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

}
