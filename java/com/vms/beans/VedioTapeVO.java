package com.vms.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	private Map<String,Integer> watching;
	
	private Date playDate;
	
	private int marked;
	
	private String comments;
	
	private List<String> examinedEmployees;
	
	private List<String> unexaminedEmployees;
	
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

	public Map<String, Integer> getWatching() {
		return watching;
	}

	public void setWatching(Map<String, Integer> watching) {
		this.watching = watching;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setMarked(int marked) {
		this.marked = marked;
	}

	public int getMarked() {
		return marked;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}

	public void setExaminedEmployees(List<String> examinedEmployees) {
		this.examinedEmployees = examinedEmployees;
	}

	public List<String> getExaminedEmployees() {
		return examinedEmployees;
	}

	public void setUnexaminedEmployees(List<String> unexaminedEmployees) {
		this.unexaminedEmployees = unexaminedEmployees;
	}

	public List<String> getUnexaminedEmployees() {
		return unexaminedEmployees;
	}
	
}
