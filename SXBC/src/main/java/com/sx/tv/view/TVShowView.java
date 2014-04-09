package com.sx.tv.view;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sx.tv.entites.Company;
import com.sx.tv.entites.People;
import com.sx.tv.entites.Progress;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.Theme;
import com.sx.tv.entites.User;

public class TVShowView {
	
	private Long id;
	private String name;

	private int count;

	private String outline;

	private String scriptSrc;

	private Progress progress;

	private Theme theme;

	private Company company;

	private Status status;

	private List<People> actors;

	private List<People> directors;

	private List<People> screenwriters;

	private List<People> publisher;
	private List<People> producer;
	
	
	private User inputter;

	private User projector;

	private Float ratings;

	private Float marketShare;

	private int ranking;
	
	private String marketAssessment;

	private Boolean isPurchase;
	
	private String comments;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date inputDate;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date rejectDate;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date playDate;
	
	private int forcePurchase;
	
	public String getName() {
		return name;
	}

	public Float getRatings() {
		return ratings;
	}

	public void setRatings(Float ratings) {
		this.ratings = ratings;
	}

	public Float getMarketShare() {
		return marketShare;
	}

	public void setMarketShare(Float marketShare) {
		this.marketShare = marketShare;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getMarketAssessment() {
		return marketAssessment;
	}

	public void setMarketAssessment(String marketAssessment) {
		this.marketAssessment = marketAssessment;
	}

	public Boolean getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(Boolean isPurchase) {
		this.isPurchase = isPurchase;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getScriptSrc() {
		return scriptSrc;
	}

	public void setScriptSrc(String scriptSrc) {
		this.scriptSrc = scriptSrc;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<People> getActors() {
		return actors;
	}

	public void setActors(List<People> actors) {
		this.actors = actors;
	}

	public List<People> getDirectors() {
		return directors;
	}

	public void setDirectors(List<People> directors) {
		this.directors = directors;
	}

	public List<People> getScreenwriters() {
		return screenwriters;
	}

	public void setScreenwriters(List<People> screenwriters) {
		this.screenwriters = screenwriters;
	}

	public List<People> getPublisher() {
		return publisher;
	}

	public void setPublisher(List<People> publisher) {
		this.publisher = publisher;
	}

	public User getInputter() {
		return inputter;
	}

	public void setInputter(User inputter) {
		this.inputter = inputter;
	}

	public User getProjector() {
		return projector;
	}

	public void setProjector(User projector) {
		this.projector = projector;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getForcePurchase() {
		return forcePurchase;
	}

	public void setForcePurchase(int forcePurchase) {
		this.forcePurchase = forcePurchase;
	}

	public List<People> getProducer() {
		return producer;
	}

	public void setProducer(List<People> producer) {
		this.producer = producer;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}
	
}
