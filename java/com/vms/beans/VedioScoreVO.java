package com.vms.beans;

import java.util.Date;

import com.vms.db.bean.Vedioscore;

public class VedioScoreVO {
	private String vedioName;
	private String examiner;
	private float storyScore;
	private float techScore;
	private float performScore;
	private float innovateScore;
	private float score;
	private float precision;
	private Date dateExamine;
	
	public VedioScoreVO(){}
	
	public VedioScoreVO(Vedioscore score){
		this.vedioName = score.getVedioID().getVedioName();
		this.examiner = score.getExaminer().getUserName();
		this.storyScore = score.getStoryScore();
		this.techScore = score.getTechScore();
		this.performScore = score.getPerformScore();
		this.innovateScore = score.getInnovateScore();
		this.score = score.getScore();
		this.precision = null==score.getPrecision()?0:score.getPrecision();
		this.dateExamine = score.getDateExamine();
	}
	
	public String getVedioName() {
		return vedioName;
	}
	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}
	public String getExaminer() {
		return examiner;
	}
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	public float getStoryScore() {
		return storyScore;
	}
	public void setStoryScore(float storyScore) {
		this.storyScore = storyScore;
	}
	public float getTechScore() {
		return techScore;
	}
	public void setTechScore(float techScore) {
		this.techScore = techScore;
	}
	public float getPerformScore() {
		return performScore;
	}
	public void setPerformScore(float performScore) {
		this.performScore = performScore;
	}
	public float getInnovateScore() {
		return innovateScore;
	}
	public void setInnovateScore(float innovateScore) {
		this.innovateScore = innovateScore;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getPrecision() {
		return precision;
	}
	public void setPrecision(float precision) {
		this.precision = precision;
	}

	public void setDateExamine(Date dateExamine) {
		this.dateExamine = dateExamine;
	}

	public Date getDateExamine() {
		return dateExamine;
	}
	
}
