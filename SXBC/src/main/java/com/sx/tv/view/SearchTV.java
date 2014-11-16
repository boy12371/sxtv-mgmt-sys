package com.sx.tv.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.Company;
import com.sx.tv.entites.Progress;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.Theme;
import com.sx.tv.entites.User;

public class SearchTV implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String priceRange;

	private Company company;

	private Progress progress;

	private Theme theme;

	private List<Status> status;

	private Channel recommendChannel;

	private Channel prjRecommendChannel;

	@DateTimeFormat(iso = ISO.DATE)
	private Date recommendTime;

	@DateTimeFormat(iso = ISO.DATE)
	private Date replyTime;

	private String publicPrice;

	private Boolean isPurchase;

	private User projector;

	private RecommendClass recommendLevel;

	private RecommendClass projectorRecommend;

	/*
	 * 播出平台(合同签约平台)
	 */
	private Channel playChannel;

	@DateTimeFormat(iso = ISO.DATE)
	private Date playDateStart;

	@DateTimeFormat(iso = ISO.DATE)
	private Date playDateEnd;

	@DateTimeFormat(iso = ISO.DATE)
	private Date copyrightFrom;

	@DateTimeFormat(iso = ISO.DATE)
	private Date copyrightTo;

	@DateTimeFormat(iso = ISO.DATE)
	private Date inputDateStart;

	@DateTimeFormat(iso = ISO.DATE)
	private Date inputDateEnd;

	
	@DateTimeFormat(iso = ISO.DATE)
	private Date ctcInputDateStart;

	@DateTimeFormat(iso = ISO.DATE)
	private Date ctcInputDateEnd;
	
	private int forcePurchase = 0;
	
	public Channel getPlayChannel() {
		return playChannel;
	}

	public void setPlayChannel(Channel playChannel) {
		this.playChannel = playChannel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public Channel getRecommendChannel() {
		return recommendChannel;
	}

	public void setRecommendChannel(Channel recommendChannel) {
		this.recommendChannel = recommendChannel;
	}

	public Channel getPrjRecommendChannel() {
		return prjRecommendChannel;
	}

	public void setPrjRecommendChannel(Channel prjRecommendChannel) {
		this.prjRecommendChannel = prjRecommendChannel;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(String publicPrice) {
		this.publicPrice = publicPrice;
	}

	public Boolean getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(Boolean isPurchase) {
		this.isPurchase = isPurchase;
	}

	public User getProjector() {
		return projector;
	}

	public void setProjector(User projector) {
		this.projector = projector;
	}

	public RecommendClass getRecommendLevel() {
		return recommendLevel;
	}

	public void setRecommendLevel(RecommendClass recommendLevel) {
		this.recommendLevel = recommendLevel;
	}

	public RecommendClass getProjectorRecommend() {
		return projectorRecommend;
	}

	public void setProjectorRecommend(RecommendClass projectorRecommend) {
		this.projectorRecommend = projectorRecommend;
	}

	public Date getCopyrightFrom() {
		return copyrightFrom;
	}

	public void setCopyrightFrom(Date copyrightFrom) {
		this.copyrightFrom = copyrightFrom;
	}

	public Date getCopyrightTo() {
		return copyrightTo;
	}

	public void setCopyrightTo(Date copyrightTo) {
		this.copyrightTo = copyrightTo;
	}

	public Date getPlayDateStart() {
		return playDateStart;
	}

	public void setPlayDateStart(Date playDateStart) {
		this.playDateStart = playDateStart;
	}

	public Date getPlayDateEnd() {
		return playDateEnd;
	}

	public void setPlayDateEnd(Date playDateEnd) {
		this.playDateEnd = playDateEnd;
	}

	public Date getInputDateStart() {
		return inputDateStart;
	}

	public void setInputDateStart(Date inputDateStart) {
		this.inputDateStart = inputDateStart;
	}

	public Date getInputDateEnd() {
		return inputDateEnd;
	}

	public void setInputDateEnd(Date inputDateEnd) {
		this.inputDateEnd = inputDateEnd;
	}

	public Date getCtcInputDateStart() {
		return ctcInputDateStart;
	}

	public void setCtcInputDateStart(Date ctcInputDateStart) {
		this.ctcInputDateStart = ctcInputDateStart;
	}

	public Date getCtcInputDateEnd() {
		return ctcInputDateEnd;
	}

	public void setCtcInputDateEnd(Date ctcInputDateEnd) {
		this.ctcInputDateEnd = ctcInputDateEnd;
	}

	public int getForcePurchase() {
		return forcePurchase;
	}

	public void setForcePurchase(int forcePurchase) {
		this.forcePurchase = forcePurchase;
	}

}
