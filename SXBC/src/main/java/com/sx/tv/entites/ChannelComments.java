package com.sx.tv.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "channelComments", finders = { "findChannelCommentsesByChannelAndTvshow", "findChannelCommentsesByTvshow" })
public class ChannelComments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Channel channel;

	@Column(name = "comments")
	private String comments;

	@Column(name = "decision")
	private boolean decision;

	@Column(name = "owner")
	private String ownedby;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private RecommendClass recommendLevel;

	/**
	 * 录入日期
	 */
	@Column(name = "recordDate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date recordDate;

	
	/**
	 * 频道反馈信息日期即是频道审核完成返回给中心的日期
	 */
	@Column(name = "recommendDate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date recommendDate;

	@Column(name = "promotionPlan")
	private String promotionPlan;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private TVShow tvshow;

	public ChannelComments() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public boolean isDecision() {
		return decision;
	}

	public void setDecision(boolean decision) {
		this.decision = decision;
	}

	public String getOwnedby() {
		return ownedby;
	}

	public void setOwnedby(String ownedby) {
		this.ownedby = ownedby;
	}

	public RecommendClass getRecommendLevel() {
		return recommendLevel;
	}

	public void setRecommendLevel(RecommendClass recommendLevel) {
		this.recommendLevel = recommendLevel;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getRecommendDate() {
		return recommendDate;
	}

	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}

	public String getPromotionPlan() {
		return promotionPlan;
	}

	public void setPromotionPlan(String promotionPlan) {
		this.promotionPlan = promotionPlan;
	}

	public TVShow getTvshow() {
		return tvshow;
	}

	public void setTvshow(TVShow tvshow) {
		this.tvshow = tvshow;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
