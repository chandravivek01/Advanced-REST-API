package com.vcs.advancedRestApi.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserV2 {
	
	private Integer id;
	
	@JsonProperty("user_name")
	private Name name; 
	
	@Past(message = "Date of birth should be in the past.")
	@JsonProperty("birth_date")
	private LocalDate birthDate;

}
