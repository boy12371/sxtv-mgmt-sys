package com.sx.tv.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@RooJpaActiveRecord(table = "tvcontract", finders = { "findTVContractsByTvshow" })
public class TVContract {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@Column(name = "owner")
	private String ownedby;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date showDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private TVShow tvshow;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Channel channel;

	@Column(name = "publishForm")
	private String publishForm;

	@Column(name = "totalPrice")
	private Integer totalPrice;

	@Column(name = "extraFee")
	private Integer extraFee;

	@Column(name = "price")
	private Integer price;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date recieveDate;

	@Column(name = "recieveOwner")
	private String recieveOwner;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date inputDate;

	@Column(name = "inputter")
	private String inputter;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date fileDate;

	@Column(name = "fileBy")
	private String fileBy;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date recieveAgreementDate;

	@Column
	private String recieveAgreementOwner;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date inputAgreementDate;

	@Column
	private String inputterAgreement;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date fileAgreementDate;

	@Column
	private String fileByAgreement;

	@Column(name = "contractNo")
	private String contractNo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date copyrightStart;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date copyrightEnd;
	
	@Column(name = "comments")
	private String comments;
	

	/**
	 * 延期几年 (-1=无限期)
	 */
	@Column(name = "exYears")
	private int exYears;

	/**
	 * 依据哪个日期延期 0=入库日期 1=母带接收日期 2=上星日期
	 */
	@Column(name = "extension")
	private int extension;

	/**
	 * 母带接收日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date tapeReciveDate;

	/**
	 * 母带入库日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date tapeStoreDate;
	

	public int getExYears() {
		return exYears;
	}

	public void setExYears(int exYears) {
		this.exYears = exYears;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public Date getTapeReciveDate() {
		return tapeReciveDate;
	}

	public void setTapeReciveDate(Date tapeReciveDate) {
		this.tapeReciveDate = tapeReciveDate;
	}

	public Date getTapeStoreDate() {
		return tapeStoreDate;
	}

	public void setTapeStoreDate(Date tapeStoreDate) {
		this.tapeStoreDate = tapeStoreDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
