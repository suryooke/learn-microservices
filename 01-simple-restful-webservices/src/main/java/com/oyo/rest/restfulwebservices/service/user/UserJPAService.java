package com.oyo.rest.restfulwebservices.service.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oyo.rest.restfulwebservices.model.user.User;
import com.oyo.rest.restfulwebservices.repository.UserRepository;

@Component
public class UserJPAService implements Serializable{
	
	private static final long serialVersionUID = -5675720652491618063L;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User getById(int id) {
		User user = userRepository.findById(id).get();
		return user;
		
	}
	
	public User save(User user) {
		userRepository.save(user);
		
		return user;
	}
	
	public User delete(int id) {
		User user = userRepository.findById(id).get();
		
		if (user != null) {
			userRepository.delete(user);
			return user;
		}
		
		return null;
	}

}
