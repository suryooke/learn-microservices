package com.oyo.rest.restfulwebservices.controller.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oyo.rest.restfulwebservices.controller.user.exception.UserNotFoundException;
import com.oyo.rest.restfulwebservices.model.user.User;
import com.oyo.rest.restfulwebservices.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//GET all user
	@GetMapping()
	public List<User> getAllUser(){
		log.info("GET /user/");
		return userService.findAll();
	}
	
	//Get user by id
	@GetMapping("/{id}")
	public EntityModel<User> getById(@PathVariable Integer id) {
		log.info("GET /user/" + id);
		User userSearch = userService.userFindById(id);
		
		if(userSearch == null)
			throw new UserNotFoundException("user-id=" + id);
		
		EntityModel<User> model = EntityModel.of(userSearch);
		
		WebMvcLinkBuilder linkToUsers = 
				linkTo(methodOn(this.getClass()).getAllUser());
		
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	//Post user
	@PostMapping()
	public ResponseEntity<User> addUser( @Valid @RequestBody User user) {
		log.info("POST /user/");
		User savedUser = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		log.info("DELETE /user/" + id);
		User userDelete = userService.delete(id);
		
		if(userDelete == null)
			throw new UserNotFoundException("user-id=" + id);
		
		return ResponseEntity.ok().build();
	}

}
