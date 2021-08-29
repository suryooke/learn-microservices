package com.oyo.rest.restfulwebservices.model.hello;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldBean {
	
	private String message;
	
	public HelloWorldBean(String message) {
		this.setMessage(message);
	}

	public String toString() {
		return String.format("HelloWorldBean - [message=%s] ", message);
	}
	
	
}
