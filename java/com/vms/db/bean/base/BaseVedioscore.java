package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the vedioscore table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="vedioscore"
 */

public abstract class BaseVedioscore  implements Serializable {

	public static String REF = "Vedioscore";
	public static String PROP_INNOVATE_SCORE = "innovateScore";
	public static String PROP_EXAMINER = "examiner";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_DATE_EXAMINE = "dateExamine";
	public static String PROP_TECH_SCORE = "techScore";
	public static String PROP_PERFORM_SCORE = "performScore";
	public static String PROP_VEDIO_I_D = "vedioID";
	public static String PROP_PURCHASE = "purchase";
	public static String PROP_PRECISION = "precision";
	public static String PROP_ID = "id";
	public static String PROP_STORY_SCORE = "storyScore";
	public static String PROP_SCORE = "score";
	public static String PROP_AWARD = "award";


	// constructors
	public BaseVedioscore () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseVedioscore (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseVedioscore (
		java.lang.Integer id,
		com.vms.db.bean.Vediotape vedioID,
		com.vms.db.bean.User examiner,
		java.lang.Float storyScore,
		java.lang.Float techScore,
		java.lang.Float performScore,
		java.lang.Float innovateScore,
		java.lang.Float score,
		java.lang.Integer award,
		java.lang.Float precision,
		java.lang.Integer purchase,
		java.util.Date dateExamine) {

		this.setId(id);
		this.setVedioID(vedioID);
		this.setExaminer(examiner);
		this.setStoryScore(storyScore);
		this.setTechScore(techScore);
		this.setPerformScore(performScore);
		this.setInnovateScore(innovateScore);
		this.setScore(score);
		this.setAward(award);
		this.setPrecision(precision);
		this.setPurchase(purchase);
		this.setDateExamine(dateExamine);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Float storyScore;
	private java.lang.Float techScore;
	private java.lang.Float performScore;
	private java.lang.Float innovateScore;
	private java.lang.Float score;
	private java.lang.Integer award;
	private java.lang.Float precision;
	private java.lang.Integer purchase;
	private java.util.Date dateExamine;
	private java.lang.String comments;

	// many to one
	private com.vms.db.bean.Vediotape vedioID;
	private com.vms.db.bean.User examiner;



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
	 * Return the value associated with the column: storyScore
	 */
	public java.lang.Float getStoryScore () {
		return storyScore;
	}

	/**
	 * Set the value related to the column: storyScore
	 * @param storyScore the storyScore value
	 */
	public void setStoryScore (java.lang.Float storyScore) {
		this.storyScore = storyScore;
	}



	/**
	 * Return the value associated with the column: techScore
	 */
	public java.lang.Float getTechScore () {
		return techScore;
	}

	/**
	 * Set the value related to the column: techScore
	 * @param techScore the techScore value
	 */
	public void setTechScore (java.lang.Float techScore) {
		this.techScore = techScore;
	}



	/**
	 * Return the value associated with the column: performScore
	 */
	public java.lang.Float getPerformScore () {
		return performScore;
	}

	/**
	 * Set the value related to the column: performScore
	 * @param performScore the performScore value
	 */
	public void setPerformScore (java.lang.Float performScore) {
		this.performScore = performScore;
	}



	/**
	 * Return the value associated with the column: innovateScore
	 */
	public java.lang.Float getInnovateScore () {
		return innovateScore;
	}

	/**
	 * Set the value related to the column: innovateScore
	 * @param innovateScore the innovateScore value
	 */
	public void setInnovateScore (java.lang.Float innovateScore) {
		this.innovateScore = innovateScore;
	}



	/**
	 * Return the value associated with the column: score
	 */
	public java.lang.Float getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: score
	 * @param score the score value
	 */
	public void setScore (java.lang.Float score) {
		this.score = score;
	}



	/**
	 * Return the value associated with the column: award
	 */
	public java.lang.Integer getAward () {
		return award;
	}

	/**
	 * Set the value related to the column: award
	 * @param award the award value
	 */
	public void setAward (java.lang.Integer award) {
		this.award = award;
	}



	/**
	 * Return the value associated with the column: precision
	 */
	public java.lang.Float getPrecision () {
		return precision;
	}

	/**
	 * Set the value related to the column: precision
	 * @param precision the precision value
	 */
	public void setPrecision (java.lang.Float precision) {
		this.precision = precision;
	}



	/**
	 * Return the value associated with the column: purchase
	 */
	public java.lang.Integer getPurchase () {
		return purchase;
	}

	/**
	 * Set the value related to the column: purchase
	 * @param purchase the purchase value
	 */
	public void setPurchase (java.lang.Integer purchase) {
		this.purchase = purchase;
	}



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
	 * Return the value associated with the column: examiner
	 */
	public com.vms.db.bean.User getExaminer () {
		return examiner;
	}

	/**
	 * Set the value related to the column: examiner
	 * @param examiner the examiner value
	 */
	public void setExaminer (com.vms.db.bean.User examiner) {
		this.examiner = examiner;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Vedioscore)) return false;
		else {
			com.vms.db.bean.Vedioscore vedioscore = (com.vms.db.bean.Vedioscore) obj;
			if (null == this.getId() || null == vedioscore.getId()) return false;
			else return (this.getId().equals(vedioscore.getId()));
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