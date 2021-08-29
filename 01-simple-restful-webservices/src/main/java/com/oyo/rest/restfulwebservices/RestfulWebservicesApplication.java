package com.oyo.rest.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RestfulWebservicesApplication {

	public static void main(String[] args) {
		log.info("Application started .........");
		SpringApplication.run(RestfulWebservicesApplication.class, args);
	}

}
