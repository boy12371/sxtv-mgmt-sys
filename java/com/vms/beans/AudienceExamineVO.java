package com.vms.beans;

import java.util.Date;

import com.vms.db.bean.Audience;
import com.vms.db.bean.Audiencescore;
import com.vms.db.bean.Vediotape;

public class AudienceExamineVO {
	public final static String yes="看";
	public final static String no="不看";
	
	private Integer id;
	
	private String audience;
	
	private String tapeName;
	
	private String tapeID;
	
	private String result;
	
	private Date dateExamine;
	
	public AudienceExamineVO(){}
	
	public AudienceExamineVO(Audiencescore as){
		this.id = as.getId();
		this.audience = as.getAudienceID().getName();
		this.tapeID = as.getVedioID().getId();
		this.tapeName = as.getVedioID().getVedioName();
		this.result = as.getResult()==0?no:yes;
		this.dateExamine = as.getDateExamine();
	}
	
	public Audiencescore toAudiencescore(){
		Audiencescore as = new Audiencescore();
		as.setVedioID(new Vediotape(tapeID));
		if(result.equals(yes)){
			as.setResult(1);
		}else{
			as.setResult(0);
		}
		if(null == dateExamine){
			as.setDateExamine(new Date());
		}else{
			as.setDateExamine(dateExamine);
		}
		if(null != id){
			as.setId(id);
		}
		Audience au = new Audience();
		au.setName(audience);
		as.setAudienceID(au);
		return as;
	}
		
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public String getAudience() {
		return audience;
	}

	public void setDateExamine(Date dateExamine) {
		this.dateExamine = dateExamine;
	}
	public Date getDateExamine() {
		return dateExamine;
	}

	public void setTapeID(String tapeID) {
		this.tapeID = tapeID;
	}

	public String getTapeID() {
		return tapeID;
	}

	public void setTapeName(String tapeName) {
		this.tapeName = tapeName;
	}

	public String getTapeName() {
		return tapeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
