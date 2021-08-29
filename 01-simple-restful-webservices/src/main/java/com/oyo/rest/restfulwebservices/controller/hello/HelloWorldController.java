package com.oyo.rest.restfulwebservices.controller.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oyo.rest.restfulwebservices.model.hello.HelloWorldBean;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloWorldController {
	
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
}
