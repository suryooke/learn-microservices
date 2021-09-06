package com.oyo.rest.restfulwebservices.controller.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oyo.rest.restfulwebservices.controller.user.exception.UserNotFoundException;
import com.oyo.rest.restfulwebservices.model.user.Post;
import com.oyo.rest.restfulwebservices.model.user.User;
import com.oyo.rest.restfulwebservices.repository.PostRepository;
import com.oyo.rest.restfulwebservices.service.user.UserJPAService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/jpa/user")
public class UserJPAController {

	
	@Autowired
	private UserJPAService userJPAService;
	
	@Autowired
	private PostRepository postRepository;
	
	
	//GET all user
	@GetMapping()
	public List<User> getAllUser(){
		log.info("GET /user/");
		return userJPAService.findAll();
	}
	
	//Get user by id
	@GetMapping("/{id}")
	public EntityModel<User> getById(@PathVariable Integer id) {
		log.info("GET /user/" + id);
		User userSearch = userJPAService.getById(id);
		
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
		User savedUser = userJPAService.save(user);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		log.info("DELETE /user/" + id);
		User userDelete = userJPAService.delete(id);
		
		if(userDelete == null)
			throw new UserNotFoundException("user-id=" + id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/posts")
	public List<Post> getAllPostByUser(@PathVariable Integer id){
		log.info("GET /{id}/posts");
		Optional<User> userOptional = Optional.of(userJPAService.getById(id));
		
		if (!userOptional.isPresent()) {
			log.info("User Not found......");
			throw new UserNotFoundException("id=" + id);
		}
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/{id}/posts")
	public ResponseEntity<User> addUser( @Valid @RequestBody Post post, @PathVariable Integer id) {
		Optional<User> userOptional = Optional.of(userJPAService.getById(id));
		
		if (!userOptional.isPresent()) {
			log.info("User Not found......");
			throw new UserNotFoundException("id=" + id);
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}/post")
			.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
