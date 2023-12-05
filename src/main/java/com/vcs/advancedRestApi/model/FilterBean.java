package com.vcs.advancedRestApi.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;

//@JsonIgnoreProperties("field1") // @JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeFilter")
@AllArgsConstructor
public class FilterBean {
	
	private String field1;
	
	//@JsonIgnore
	private String field2;
	
	private String field3;
	
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	public String getField3() {
		return field3;
	}
}
