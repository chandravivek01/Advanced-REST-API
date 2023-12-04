package com.vcs.advancedRestApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Name {
	
	@Size(min = 2, message = "First-name should be at least 2 characters long.")
	@JsonProperty("first_name")
	private String firstName;
	
	@Size(min = 2, message = "Last-name should be at least 2 characters long.")
	@JsonProperty("last_name")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
