package com.vms.beans;

import java.util.Date;

import com.vms.db.bean.Audiencescore;

public class AudienceExamineVO {
	public final static String yes="看";
	public final static String no="不看";
	
	private String audience;
	
	private String tape;
	
	private String result;
	
	private Date dateExamine;
	
	public AudienceExamineVO(Audiencescore as){
		this.audience = as.getAudienceID().getName();
		this.tape = as.getVedioID().getVedioName();
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
	public void setTape(String tape) {
		this.tape = tape;
	}
	public String getTape() {
		return tape;
	}
	public void setDateExamine(Date dateExamine) {
		this.dateExamine = dateExamine;
	}
	public Date getDateExamine() {
		return dateExamine;
	}
}
