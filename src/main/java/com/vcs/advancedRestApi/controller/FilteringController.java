package com.vcs.advancedRestApi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.vcs.advancedRestApi.model.FilterBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public FilterBean staticfiltering() {
		return new FilterBean("val1", "val2", "val3");
	}
	
	@GetMapping("/filtering-list")
	public List<FilterBean> staticfilteringList() {
		return Arrays.asList(new FilterBean("val1", "val2", "val3"), 
				new FilterBean("val4", "val5", "val6"));
	}
	
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue dynamicfiltering() {
		
		FilterBean filterBean = new FilterBean("val1", "val2", "val3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue dynamicFilteringList() {
		
		List<FilterBean> filterList = Arrays.asList(new FilterBean("val1", "val2", "val3"),
													new FilterBean("val4", "val5", "val6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterList);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	
}
