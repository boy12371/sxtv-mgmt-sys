package com.vms.beans;

import java.util.Date;

import com.vms.db.bean.Audiencescore;

public class AudienceExamineVO {
	public final static String yes="看";
	public final static String no="不看";
	
	private String audience;
	
	private String tapeName;
	
	private String tapeID;
	
	private String result;
	
	private Date dateExamine;
	
	public AudienceExamineVO(Audiencescore as){
		this.audience = as.getAudienceID().getName();
		this.tapeID = as.getVedioID().getId();
		this.tapeName = as.getVedioID().getVedioName();
		this.result = as.getResult()==0?no:yes;
		this.dateExamine = as.getDateExamine();
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
}
