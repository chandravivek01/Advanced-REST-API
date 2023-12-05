package com.vcs.advancedRestApi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vcs.advancedRestApi.exception.UserNotFoundException;
import com.vcs.advancedRestApi.model.Post;
import com.vcs.advancedRestApi.model.User;
import com.vcs.advancedRestApi.repository.PostRepository;
import com.vcs.advancedRestApi.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id ) {
		
		User user = userRepository.findById(id).get();
		if ( user == null )
			throw new UserNotFoundException("id: " + id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPostsOfAUser(@PathVariable int id) {
		
		User user = userRepository.findById(id).get();
		if ( user == null )
			throw new UserNotFoundException("id: " + id);
		
		return user.getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		
		User user = userRepository.findById(id).get();
		if ( user == null )
			throw new UserNotFoundException("id: " + id);
		
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location ).build();
	}
}
