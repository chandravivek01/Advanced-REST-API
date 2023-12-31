package com.vcs.advancedRestApi.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vcs.advancedRestApi.dao.UserDaoService;
import com.vcs.advancedRestApi.exception.UserNotFoundException;
import com.vcs.advancedRestApi.model.User1;
import com.vcs.advancedRestApi.model.UserV2;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User1> getAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User1> retrieveUserById(@PathVariable int id ) {
		
		User1 user = service.findUserById(id);
		if ( user == null )
			throw new UserNotFoundException("id: " + id);
		
		EntityModel<User1> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User1> createUser(@Valid @RequestBody User1 user) {
		
		User1 savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUserById(id);
	}
	
	@GetMapping("/users/message")
	public String greet() {
		
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
	
	// Version-2
	
	// Version by URL
	@GetMapping("/v2/users")
	public List<UserV2> getAllUsersV2() {
		return service.findAll2();
	}
	
	// version by Request-Parameter
	@GetMapping(path = "/users/{id2}", params = "version=2" )
	public UserV2 retrieveUserById2(@PathVariable int id2) {
		
		UserV2 userV2 = service.findUserById2(id2);
		if ( userV2 == null )
			throw new UserNotFoundException("id: " + id2);
		
		return userV2;
	}
	
	// version by custom-headers
	@PostMapping(path = "/users/header", headers = "api-version=2")
	public ResponseEntity<UserV2> createUser2(@Valid @RequestBody UserV2 userV2) {
		
		UserV2 savedUserV2 = service.save2(userV2);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserV2.getId()).toUri();
		return ResponseEntity.created(location ).build();
	}
	
	// version by media-type
	@GetMapping(path = "/users/accept", produces="application/vnd.company.app-v2+json")
	public List<UserV2> getAllUsersv2() {
		return service.findAll2();
	}
}
