package com.vms.db.bean.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the scoreweight table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="scoreweight"
 */

public abstract class BaseScoreweight  implements Serializable {

	public static String REF = "Scoreweight";
	public static String PROP_WIEGHT = "wieght";
	public static String PROP_ID = "id";


	// constructors
	public BaseScoreweight () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseScoreweight (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseScoreweight (
		java.lang.String id,
		java.lang.Float wieght) {

		this.setId(id);
		this.setWieght(wieght);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Float wieght;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="factor"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: wieght
	 */
	public java.lang.Float getWieght () {
		return wieght;
	}

	/**
	 * Set the value related to the column: wieght
	 * @param wieght the wieght value
	 */
	public void setWieght (java.lang.Float wieght) {
		this.wieght = wieght;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Scoreweight)) return false;
		else {
			com.vms.db.bean.Scoreweight scoreweight = (com.vms.db.bean.Scoreweight) obj;
			if (null == this.getId() || null == scoreweight.getId()) return false;
			else return (this.getId().equals(scoreweight.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}