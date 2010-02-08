package com.vms.beans;

import java.util.Date;

import com.vms.db.bean.Vediotape;

public class VedioTapeVO {
	private String vedioID;
	private String name;
	private String subject;
	private String topic;
	private String company;
	private Date dateComing;
	private String status;
	private float marketShare;
	private float audienceRating;
	
	public VedioTapeVO(){}
	
	public VedioTapeVO(Vediotape tape){
		this.vedioID = tape.getId();
		this.name = tape.getVedioName();
		this.subject = tape.getSubject().getSubjectName();
		this.topic = tape.getTopic().getTopicName();
		this.company = tape.getCompanyID().getCompanyName();
		this.dateComing = tape.getDateComing();
		this.status = tape.getStatus().getStatus();
		this.marketShare = null==tape.getMarketShare() ? 0 : tape.getMarketShare();
		this.audienceRating = null==tape.getAudienceRating() ? 0 : tape.getAudienceRating();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public java.util.Date getDateComing() {
		return dateComing;
	}
	public void setDateComing(java.util.Date dateComing) {
		this.dateComing = dateComing;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getMarketShare() {
		return marketShare;
	}
	public void setMarketShare(float marketShare) {
		this.marketShare = marketShare;
	}
	public float getAudienceRating() {
		return audienceRating;
	}
	public void setAudienceRating(float audienceRating) {
		this.audienceRating = audienceRating;
	}

	public void setVedioID(String vedioID) {
		this.vedioID = vedioID;
	}

	public String getVedioID() {
		return vedioID;
	}
	
}
