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
	public static String PROP_COMMENTS = "levelScore";
	public static String PROP_ID = "id";

	private java.lang.Integer id;
	private java.lang.Integer level;
	private java.lang.Integer start;
	private java.lang.Integer end;
	private java.lang.Float levelScore;

	private int hashCode = Integer.MIN_VALUE;

	public BaseScorelevel() {

	}

	public BaseScorelevel(Integer id) {
		this.setId(id);

	}

	public BaseScorelevel(Float levelScore, Integer end, Integer id,
			Integer level, Integer start) {
		super();
		this.levelScore = levelScore;
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

	public java.lang.Integer getStart() {
		return start;
	}

	public void setStart(java.lang.Integer start) {
		this.start = start;
	}

	public java.lang.Integer getEnd() {
		return end;
	}

	public void setEnd(java.lang.Integer end) {
		this.end = end;
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

	public java.lang.Float getLevelScore() {
		return levelScore;
	}

	public void setLevelScore(java.lang.Float levelScore) {
		this.levelScore = levelScore;
	}

}
