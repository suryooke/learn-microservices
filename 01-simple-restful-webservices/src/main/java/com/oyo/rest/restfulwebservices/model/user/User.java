package com.oyo.rest.restfulwebservices.model.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

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
	
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String name;
	
	@Past(message = "DOB should in the past")
	private Date dateOfBirth;
	
	public String toString() {
		return String.format("User - [id=%s, name=%s, dateOfBirth=%s] ", id, name, dateOfBirth);
	}

}
