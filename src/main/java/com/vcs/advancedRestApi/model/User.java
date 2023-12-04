package com.vcs.advancedRestApi.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	
	private Integer id;
	
	@JsonProperty("user_name")
	private String name;
	
	@JsonProperty("birth_date")
	private LocalDate birthDate;
}
