package com.vms.db.bean.base;

import java.io.Serializable;

public class BaseScorelevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8472673844145208988L;
	public static String REF = "Scorelevel";
	public static String PROP_LEVEL = "level";
	public static String PROP_START = "start";
	public static String PROP_END = "end";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_ID = "id";

	private java.lang.Integer id;
	private java.lang.Integer level;
	private java.lang.Float start;
	private java.lang.Float end;
	private java.lang.String comments;

	private int hashCode = Integer.MIN_VALUE;

	public BaseScorelevel() {

	}

	public BaseScorelevel(Integer id) {
		this.setId(id);

	}

	public BaseScorelevel(String comments, Float end, Integer id,
			Integer level, Float start) {
		super();
		this.comments = comments;
		this.end = end;
		this.id = id;
		this.level = level;
		this.start = start;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.Integer getLevel() {
		return level;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	public java.lang.Float getStart() {
		return start;
	}

	public void setStart(java.lang.Float start) {
		this.start = start;
	}

	public java.lang.Float getEnd() {
		return end;
	}

	public void setEnd(java.lang.Float end) {
		this.end = end;
	}

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.vms.db.bean.Scoreweight))
			return false;
		else {
			com.vms.db.bean.Scorelevel scorelevel = (com.vms.db.bean.Scorelevel) obj;
			if (null == this.getId() || null == scorelevel.getId())
				return false;
			else
				return (this.getId().equals(scorelevel.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}
