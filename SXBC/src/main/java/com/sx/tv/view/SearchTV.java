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
	private Long id;

	private String name;

	// private int count;

	// private String publishSchedule;

	private String priceRange;

	// private int ranking;

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

	// private Set<People> actors = new HashSet<People>();
	//
	// private Set<People> directors = new HashSet<People>();
	//
	// private Set<People> screenwriters = new HashSet<People>();
	//
	// private Set<People> publisher = new HashSet<People>();

	// private String marketAssessment;
}
