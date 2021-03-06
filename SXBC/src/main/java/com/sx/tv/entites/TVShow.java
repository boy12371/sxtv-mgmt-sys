package com.sx.tv.entites;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "tvshow", finders = { "findTVShowsByTheme",
		"findTVShowsByCompany", "findTVShowsByCount",
		"findTVShowsByInputDateBetween", "findTVShowsByInputDateEquals",
		"findTVShowsByInputter", "findTVShowsByIsPurchaseNot",
		"findTVShowsByNameEquals", "findTVShowsByPriceRangeLike",
		"findTVShowsByProgress", "findTVShowsByStatus",
		"findTVShowsByNameLike", "findTVShowsByScreenwriters" })
public class TVShow {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "count")
	@Value("0")
	private int count;

	@Column(name = "ratings")
	@Value("0")
	private Float ratings;

	@Column(name = "marketShare")
	@Value("0")
	private Float marketShare;

	@Column(name = "ranking")
	@Value("0")
	private int ranking;

	@Column(name = "outline")
	private String outline;

	@Column(name = "marketAssessment")
	@Value("-")
	private String marketAssessment;

	@Column(name = "isPurchase")
	@Value("false")
	private Boolean isPurchase;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date inputDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date rejectDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date playDate;

	@Column(name = "scriptSrc", length = 512)
	private String scriptSrc;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Progress progress;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Theme theme;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Status status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<People> actors;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<People> directors;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<People> screenwriters;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<People> publisher;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<People> producer;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private User inputter;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private User projector;

	@Value(value = "")
	private String comments;

	/**
	 * default 0=Normal, 1=removed. only used for remove the record.
	 * 
	 */
	@Column(name = "removed")
	private Integer removed = 0;

	/**
	 * default 0=Normal, 1=forcePurchase. 应频道要求购买.
	 * 
	 */
	@Column(name = "forcePurchase")
	private Integer forcePurchase = 0;
	
	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
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

	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
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

	public Integer getRemoved() {
		return removed;
	}

	public void setRemoved(Integer removed) {
		this.removed = removed;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public String getScriptSrc() {
		return scriptSrc;
	}

	public void setScriptSrc(String scriptSrc) {
		this.scriptSrc = scriptSrc;
	}

	public Set<People> getActors() {
		return actors;
	}

	public void setActors(Set<People> actors) {
		this.actors = actors;
	}

	public Set<People> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<People> directors) {
		this.directors = directors;
	}

	public Set<People> getScreenwriters() {
		return screenwriters;
	}

	public void setScreenwriters(Set<People> screenwriters) {
		this.screenwriters = screenwriters;
	}

	public Set<People> getPublisher() {
		return publisher;
	}

	public void setPublisher(Set<People> publisher) {
		this.publisher = publisher;
	}

	public Integer getForcePurchase() {
		return forcePurchase;
	}

	public void setForcePurchase(Integer forcePurchase) {
		this.forcePurchase = forcePurchase;
	}

	public Set<People> getProducer() {
		return producer;
	}

	public void setProducer(Set<People> producer) {
		this.producer = producer;
	}

}
