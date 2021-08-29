package com.oyo.rest.restfulwebservices.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public User getById(@PathVariable Integer id) {
		log.info("GET /user/" + id);
		return userService.UserfindOne(id);
	}
	
	//Post user
	@PostMapping()
	public User addUser(@RequestBody User user) {
		log.info("POST /user/");
		return userService.save(user);
	}

}
