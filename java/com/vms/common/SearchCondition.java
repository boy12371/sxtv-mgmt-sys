package com.vms.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vms.db.bean.Company;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;

public class SearchCondition {
	
	private String id;
	private String name;
	private Company company;
	private Subject subject;
	private Topic topic;
	private Status status;
	
	private String startDate;
	private String endDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {		
		return endDate;
	}
	
	public Date getEndingDate() throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(this.getEndDate()!=null && !"".equals(this.getEndDate())){
			return format.parse(this.getEndDate());	
		}
		return null;
		
	}
	
	public Date getStartingDate() throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(this.getStartDate()!=null && !"".equals(this.getStartDate())){
			return format.parse(this.getStartDate());	
		}
		return null;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

}
