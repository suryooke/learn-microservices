package com.oyo.rest.restfulwebservices.model.user;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	public User (Integer id, String name, Date dob) {
		super();
		this.id =id;
		this.name = name;
		this.dateOfBirth = dob;
	}
	
	private Integer id;
	private String name;
	private Date dateOfBirth;
	
	public String toString() {
		return String.format("User - [id=%s, name=%s, dateOfBirth=%s] ", id, name, dateOfBirth);
	}

}
