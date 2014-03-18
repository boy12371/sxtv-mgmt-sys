package com.sx.tv.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(identifierColumn = "id", identifierField = "id", table = "playInfo", finders = { "findPlayInfoesByTvshow", "findPlayInfoesByPlayChannel",
		"findPlayInfoesByPlayDateBetween", "findPlayInfoesByPlayDateGreaterThan", "findPlayInfoesByReservedToGreaterThanEquals" })
public class PlayInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@Column(name = "round")
	private Integer round;

	@Column(name = "price")
	private Float price;

	@Column(name = "reservedFrom")
	@DateTimeFormat(iso = ISO.DATE)
	private Date reservedFrom;

	@Column(name = "reservedTo")
	@DateTimeFormat(iso = ISO.DATE)
	private Date reservedTo;

	@Column(name = "playDate")
	@DateTimeFormat(iso = ISO.DATE)
	private Date playDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Channel playChannel;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private TVShow tvshow;
}
