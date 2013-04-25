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
@RooSerializable
@RooJpaActiveRecord(table = "channel", finders = { "findChannelsByType" })
public class Channel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@NotNull(message = "Provide the name please")
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "comments")
	private String comments;

	/**
	 * 1=卫视 0=地面
	 */
	@Column(name = "type")
	private int type;

	public Channel(Integer id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public Channel() {
	}
}
