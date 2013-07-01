package com.sx.tv.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "scoreDetail")
@RooSerializable
public class ScoreDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private ScoreIndicator indicator;

	@Column(name = "scoreAmount")
	private Float scoreAmount;

	public ScoreDetail(ScoreIndicator indicator, Float scoreAmount) {
		this.indicator = indicator;
		this.scoreAmount = scoreAmount;
	}

	public ScoreDetail() {
	}

	public ScoreIndicator getIndicator() {
		return indicator;
	}

	public void setIndicator(ScoreIndicator indicator) {
		this.indicator = indicator;
	}

	public Float getScoreAmount() {
		return scoreAmount;
	}

	public void setScoreAmount(Float scoreAmount) {
		this.scoreAmount = scoreAmount;
	}

}
