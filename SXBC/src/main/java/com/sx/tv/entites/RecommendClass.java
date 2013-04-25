package com.sx.tv.entites;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "recommendClass")
@RooSerializable
public class RecommendClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id",insertable = false, updatable = false)
    private Integer id;

	
    @Column(name = "level")
    private String name;

    @Column(name = "start")
    private int start;
    
    @Column(name = "end")
    private int end;
    
	public RecommendClass(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
    
	public RecommendClass() {
		
	}
    
}
