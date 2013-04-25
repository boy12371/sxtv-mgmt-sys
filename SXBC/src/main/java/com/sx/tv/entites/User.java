package com.sx.tv.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "users", finders = { "findUsersByNameEquals",
		"findUsersByPeople", "findUsersByRoles" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@NotNull
	@Size(max = 30, min = 4)
	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "staff")
	private String staff;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();

	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public User() {
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User u = (User) obj;

		return this.id == u.getId();
	}

	@Override
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : (new Integer(0))
				.hashCode();
	}

}
