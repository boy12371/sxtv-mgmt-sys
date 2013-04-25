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
@RooJpaActiveRecord(table = "status")
@RooSerializable
public class Status {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id",insertable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name="remark")
    private String remark;
	public Status(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Status() {
	}
    
    
}
