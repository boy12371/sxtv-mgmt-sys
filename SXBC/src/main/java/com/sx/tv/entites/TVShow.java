package com.sx.tv.entites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@RooJpaActiveRecord(table = "tvshow", finders = { "findTVShowsByTheme", "findTVShowsByCompany", "findTVShowsByCount", "findTVShowsByInputDateBetween",
		"findTVShowsByInputDateEquals", "findTVShowsByInputter", "findTVShowsByIsPurchaseNot", "findTVShowsByNameEquals", "findTVShowsByPriceRangeLike",
		"findTVShowsByProgress", "findTVShowsByStatus", "findTVShowsByNameLike", "findTVShowsByScreenwriters" })
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

	@Column(name = "publishSchedule")
	private String publishSchedule;

	@Column(name = "priceRange")
	@Value("0-0")
	private String priceRange;

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

	@Column(name = "publicPrice")
	@Value("0")
	private Float publicPrice;

	@Column(name = "isPurchase")
	@Value("false")
	private Boolean isPurchase;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date inputDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date rejectDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Progress progress;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Theme theme;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Status status;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<People> actors = new ArrayList<People>();

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<People> directors = new ArrayList<People>();

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<People> screenwriters = new ArrayList<People>();

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<People> publisher = new ArrayList<People>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private User inputter;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private User projector;

	@Value(value="")
	private String comments;
	
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

	public String getPublishSchedule() {
		return publishSchedule;
	}

	public void setPublishSchedule(String publishSchedule) {
		this.publishSchedule = publishSchedule;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
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

	public Float getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(Float publicPrice) {
		this.publicPrice = publicPrice;
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

}
