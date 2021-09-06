package com.oyo.rest.restfulwebservices.model.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
	
	public User() {}
	
	public User (Integer id, String name, Date dob) {
		super();
		this.id =id;
		this.name = name;
		this.dateOfBirth = dob;
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String name;
	
	@Past(message = "DOB should in the past")
	private Date dateOfBirth;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Post> posts;
	
	
	public String toString() {
		return String.format("User - [id=%s, name=%s, dateOfBirth=%s] ", id, name, dateOfBirth);
	}

}
