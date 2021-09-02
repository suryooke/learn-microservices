package com.oyo.rest.restfulwebservices.controller.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.oyo.rest.restfulwebservices.model.hello.HelloWorldBean;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/hello")
	public String HelloWorld() {
		log.info("accessing GET /hello");
		return "Hello World.";
	}

	@GetMapping(path="/hello-bean")
	public HelloWorldBean HelloWorldBean() {
		log.info("accessing GET /hello-bean");
		return new HelloWorldBean("Hello World..");
	}
	
	@GetMapping(path="/hello-bean/{message}")
	public HelloWorldBean HelloWorldPathVariable(@PathVariable String message) {
		log.info("accessing GET /hello-bean/{message}");
		return new HelloWorldBean(message);
	}
	
	@GetMapping(path="/hello-internationalized")
	public String HelloWorldInternationalized(
			//@RequestHeader(name = "Accept-language", required = false) Locale locale
			) {
		log.info("accessing GET /hello-internationalized");
		return messageSource.getMessage("hello.world.message", null,"Default Message", 
				//locale
				LocaleContextHolder.getLocale());
	}
}
