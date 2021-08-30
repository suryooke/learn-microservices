package com.oyo.rest.restfulwebservices.controller.exception;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oyo.rest.restfulwebservices.controller.user.exception.UserNotFoundException;
import com.oyo.rest.restfulwebservices.model.exception.GenericExceptionResponse;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {

		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

//		List<FieldError> fe = ex.getBindingResult().getFieldErrors();
//		Iterator<FieldError> it = fe.iterator();
//		while(it.hasNext()) {
//			FieldError feit = it.next();
//			System.out.println(feit.toString());
//			System.out.println(feit.getDefaultMessage());
//		}
		
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), "Validation failed",
				ex.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
