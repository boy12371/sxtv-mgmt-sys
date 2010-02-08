package com.vms.db.bean.base;

import java.io.Serializable;


public abstract class BaseAudienceScorePK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private com.vms.db.bean.Audience audienceID;
	private com.vms.db.bean.Vediotape vedioID;


	public BaseAudienceScorePK () {}
	
	public BaseAudienceScorePK (
		com.vms.db.bean.Audience audienceID,
		com.vms.db.bean.Vediotape vedioID) {

		this.setAudienceID(audienceID);
		this.setVedioID(vedioID);
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
		if (!(obj instanceof com.vms.db.bean.AudienceScorePK)) return false;
		else {
			com.vms.db.bean.AudienceScorePK mObj = (com.vms.db.bean.AudienceScorePK) obj;
			if (null != this.getAudienceID() && null != mObj.getAudienceID()) {
				if (!this.getAudienceID().equals(mObj.getAudienceID())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getVedioID() && null != mObj.getVedioID()) {
				if (!this.getVedioID().equals(mObj.getVedioID())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getAudienceID()) {
				sb.append(this.getAudienceID().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getVedioID()) {
				sb.append(this.getVedioID().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}