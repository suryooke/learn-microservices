package com.oyo.rest.restfulwebservices.model.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
	
	public GenericExceptionResponse(Date tiomestamp, String message, String details) {
		super();
		this.timestamp = tiomestamp;
		this.message = message;
		this.details = details;
	}

	
}
