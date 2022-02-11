package com.oyo.microservices.currencyconversionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oyo.microservices.currencyconversionservice.model.CurrencyConversion;


@FeignClient(name="currency-exchange-service", url="localhost:8281")
public interface CurrencyExcahngeFeign {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from, 
			@PathVariable String to);
}
