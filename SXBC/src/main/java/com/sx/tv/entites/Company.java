package com.sx.tv.entites;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "company")
@RooSerializable
public class Company {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id",insertable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "publisher")
    private String publisher;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "achievements")
    private String achievements;
    
	public Company(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
    
	public Company() {
	}
}
