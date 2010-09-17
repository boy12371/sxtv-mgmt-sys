package com.vms.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Audiencescore;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;

public class VedioTapeVO{
	private String id;
	private String vedioName;
	private String subject;
	private String topic;
	private String companyID;
	private Date dateComing;
	private String status;
	private float marketShare;
	private float audienceRating;
	private Float avgScore;
	private String audiScore;
	private int orientation;
	private String award;
	
	private Map<String,Integer> watching;
	
	private Date playDate;
	
	private int marked;
	
	private String comments;
	
	private List<String> examinedEmployees;
	
	private List<String> unexaminedEmployees;
	
	public VedioTapeVO(){}
	
	public VedioTapeVO(Vediotape tape){
		this.setId(tape.getId());
		this.setVedioName(tape.getVedioName());
		this.subject = tape.getSubject().getSubjectName();
		this.topic = tape.getTopic().getTopicName();
		this.setCompanyID(tape.getCompanyID().getCompanyName());
		this.dateComing = tape.getDateComing();
		this.status = tape.getStatus().getStatus();
		this.marketShare = null==tape.getMarketShare() ? 0 : tape.getMarketShare();
		this.audienceRating = null==tape.getAudienceRating() ? 0 : tape.getAudienceRating();
		orientation = 0;
		if(null != tape.getVedioscores() && 0 != tape.getVedioscores().size()){
			float sum = 0;
			int award = 0;
			int unaward = 0;
			for(Vedioscore s:tape.getVedioscores()){
				sum += s.getScore();
				if(s.getOrientation()==1){
					orientation++;
				}
				if(s.getAward()==1){
					award++;
				}else{
					unaward++;
				}
			}
			if(0 != sum){
				this.avgScore = sum/tape.getVedioscores().size();
			}
			this.award = award + "/" + unaward;
		}
		if(null != tape.getAudiencescore() && 0 != tape.getAudiencescore().size()){
			int look = 0;
			int unlook = 0;
			for(Audiencescore s:tape.getAudiencescore()){
				if(s.getResult()==1){
					look++;
				}else{
					unlook++;
				}
			}
			this.audiScore = look+"/"+unlook;;
		}
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

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}

	public String getVedioName() {
		return vedioName;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	public Float getAvgScore() {
		return avgScore;
	}

	public void setAudiScore(String audiScore) {
		this.audiScore = audiScore;
	}

	public String getAudiScore() {
		return audiScore;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getAward() {
		return award;
	}
	
}
