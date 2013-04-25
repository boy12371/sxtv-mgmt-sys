package com.sx.tv.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private ScoreIndicator indicator;

	@Column(name = "score")
	private Float score;

	public ScoreDetail(ScoreIndicator indicator, Float score) {
		this.indicator = indicator;
		this.score = score;
	}
	public ScoreDetail() {
	}
	
}
