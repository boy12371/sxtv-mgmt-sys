package com.vms.db.bean.base;

import java.io.Serializable;

import com.vms.db.bean.Company;
import com.vms.db.bean.Vediotape;


/**
 * This is an object that contains data related to the company table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="company"
 */

public abstract class BaseCompany  implements Serializable {

	public static String REF = "Company";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_PHONE = "phone";
	public static String PROP_CONTACT_PERSON = "contactPerson";
	public static String PROP_ID = "id";
	public static String PROP_COMPANY_NAME = "companyName";
	public static String PROP_REGISTRATION_NO = "registrationNo";


	// constructors
	public BaseCompany () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCompany (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCompany (
		java.lang.Integer id,
		java.lang.String companyName,
		java.lang.String registrationNo,
		java.lang.String phone,
		java.lang.String contactPerson,
		java.lang.String comments) {

		this.setId(id);
		this.setCompanyName(companyName);
		this.setRegistrationNo(registrationNo);
		this.setPhone(phone);
		this.setContactPerson(contactPerson);
		this.setComments(comments);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String companyName;
	private java.lang.String registrationNo;
	private java.lang.String phone;
	private java.lang.String contactPerson;
	private java.lang.String comments;

	// collections
	private java.util.Set<com.vms.db.bean.Vediotape> vediotapes;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="companyID"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: companyName
	 */
	public java.lang.String getCompanyName () {
		return companyName;
	}

	/**
	 * Set the value related to the column: companyName
	 * @param companyName the companyName value
	 */
	public void setCompanyName (java.lang.String companyName) {
		this.companyName = companyName;
	}



	/**
	 * Return the value associated with the column: registrationNo
	 */
	public java.lang.String getRegistrationNo () {
		return registrationNo;
	}

	/**
	 * Set the value related to the column: registrationNo
	 * @param registrationNo the registrationNo value
	 */
	public void setRegistrationNo (java.lang.String registrationNo) {
		this.registrationNo = registrationNo;
	}



	/**
	 * Return the value associated with the column: phone
	 */
	public java.lang.String getPhone () {
		return phone;
	}

	/**
	 * Set the value related to the column: phone
	 * @param phone the phone value
	 */
	public void setPhone (java.lang.String phone) {
		this.phone = phone;
	}



	/**
	 * Return the value associated with the column: contactPerson
	 */
	public java.lang.String getContactPerson () {
		return contactPerson;
	}

	/**
	 * Set the value related to the column: contactPerson
	 * @param contactPerson the contactPerson value
	 */
	public void setContactPerson (java.lang.String contactPerson) {
		this.contactPerson = contactPerson;
	}



	/**
	 * Return the value associated with the column: comments
	 */
	public java.lang.String getComments () {
		return comments;
	}

	/**
	 * Set the value related to the column: comments
	 * @param comments the comments value
	 */
	public void setComments (java.lang.String comments) {
		this.comments = comments;
	}



	/**
	 * Return the value associated with the column: vediotapes
	 */
	public java.util.Set<com.vms.db.bean.Vediotape> getVediotapes () {
		return vediotapes;
	}

	/**
	 * Set the value related to the column: vediotapes
	 * @param vediotapes the vediotapes value
	 */
	public void setVediotapes (java.util.Set<com.vms.db.bean.Vediotape> vediotapes) {
		this.vediotapes = vediotapes;
	}

	public void addTovediotapes (com.vms.db.bean.Vediotape vediotape) {
		if (null == getVediotapes()) setVediotapes(new java.util.TreeSet<com.vms.db.bean.Vediotape>());
		getVediotapes().add(vediotape);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vms.db.bean.Company)) return false;
		else {
			com.vms.db.bean.Company company = (com.vms.db.bean.Company) obj;
			if (null == this.getId() || null == company.getId()) return false;
			else return (this.getId().equals(company.getId()));
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