package com.sx.tv.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "projectorComments", finders = { "findProjectorCommentsesByTvshow" })
public class ProjectorComments {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;
	
	@Column(name = "publicPrice")
	private String publicPrice;
	
	@Column(name = "publicSchdule")
	private String publicSchdule;
	
	@Column(name = "webSchdule")
	private String webSchdule;
	
	@Column(name = "localSchdule")
	private String localSchdule;
	
	@Column(name = "priceRange")
	private String priceRange;

	@Column(name = "isRecommend")
	private Boolean isRecommend;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private RecommendClass projectorRecommend;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Channel recommendChannel;

	@Column(name = "comments")
	private String comments;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private TVShow tvshow;

	public RecommendClass getProjectorRecommend() {
		return projectorRecommend;
	}

	public void setProjectorRecommend(RecommendClass projectorRecommend) {
		this.projectorRecommend = projectorRecommend;
	}

	public Channel getRecommendChannel() {
		return recommendChannel;
	}

	public void setRecommendChannel(Channel recommendChannel) {
		this.recommendChannel = recommendChannel;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TVShow getTvshow() {
		return tvshow;
	}

	public void setTvshow(TVShow tvshow) {
		this.tvshow = tvshow;
	}

	public String getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(String publicPrice) {
		this.publicPrice = publicPrice;
	}

	public String getPublicSchdule() {
		return publicSchdule;
	}

	public void setPublicSchdule(String publicSchdule) {
		this.publicSchdule = publicSchdule;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getWebSchdule() {
		return webSchdule;
	}

	public void setWebSchdule(String webSchdule) {
		this.webSchdule = webSchdule;
	}

	public String getLocalSchdule() {
		return localSchdule;
	}

	public void setLocalSchdule(String localSchdule) {
		this.localSchdule = localSchdule;
	}
}
