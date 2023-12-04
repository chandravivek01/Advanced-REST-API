package com.vcs.advancedRestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcs.advancedRestApi.dao.UserDaoService;
import com.vcs.advancedRestApi.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}
}
