package com.oyo.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oyo.microservices.limitsservice.configuration.Configuration;
import com.oyo.microservices.limitsservice.model.Limit;

@RestController
@RequestMapping("/limits")
public class LimitsController {
	
	@Autowired
	private Configuration config;
	
	@GetMapping
	public Limit getAllLimit() {
		
		return new Limit(config.getMinimum(), config.getMaximum());
//		return new Limit(1, 10);
	}

}
