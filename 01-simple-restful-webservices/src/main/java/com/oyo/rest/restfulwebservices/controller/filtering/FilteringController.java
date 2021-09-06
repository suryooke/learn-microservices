package com.oyo.rest.restfulwebservices.controller.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.oyo.rest.restfulwebservices.model.beans.SomeBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("someBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value1-2", "value2-2", "value3-2"),
				new SomeBean("value1-3", "value2-3", "value3-3"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field3");
		
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("someBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
