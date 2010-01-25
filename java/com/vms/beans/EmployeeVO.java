package com.vms.beans;

public class EmployeeVO {

	private java.lang.Integer id;	
	private java.lang.String name;
	private java.util.Date birthday;
	private java.util.Date contractDate;
	private java.lang.Integer gender;
	private java.lang.String tel;
	private java.lang.String comments;
	
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.util.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	public java.util.Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(java.util.Date contractDate) {
		this.contractDate = contractDate;
	}
	public java.lang.Integer getGender() {
		return gender;
	}
	public void setGender(java.lang.Integer gender) {
		this.gender = gender;
	}
	public java.lang.String getTel() {
		return tel;
	}
	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}
	public java.lang.String getComments() {
		return comments;
	}
	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

}
