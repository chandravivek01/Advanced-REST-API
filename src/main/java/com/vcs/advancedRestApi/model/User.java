package com.vcs.advancedRestApi.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user_details")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "The name should have at least 2 characters.")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Date of birth should be in the past.")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
}
