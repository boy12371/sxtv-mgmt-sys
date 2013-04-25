package com.sx.tv.view;


public class RejectTVshow {
	private int tvid;
	private int status;
	private String reason;
	private String owner;
	
	public RejectTVshow(int tvid, int status, String reason, String owner) {
		this.tvid = tvid;
		this.status = status;
		this.reason = reason;
		this.owner = owner;
	}
	
	public RejectTVshow() {
	}
	public int getTvid() {
		return tvid;
	}
	public void setTvid(int tvid) {
		this.tvid = tvid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
