package com.sx.tv.view;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.Company;
import com.sx.tv.entites.Progress;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.Theme;
import com.sx.tv.entites.User;

@RooJavaBean
@RooToString
@RooSerializable
public class SearchTV {
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

	private Status status;

	private Channel recommendChannel;

	private Channel prjRecommendChannel;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date recommendTime;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date replyTime;

	private String publicPrice;

	private Boolean isPurchase;

	private User projector;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date inputDate;

	private RecommendClass recommendLevel;

	private RecommendClass projectorRecommend;

	private Channel playChannel;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date playDateStart;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date playDateEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date copyrightFrom;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date copyrightTo;

	private String contractNo;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

}
