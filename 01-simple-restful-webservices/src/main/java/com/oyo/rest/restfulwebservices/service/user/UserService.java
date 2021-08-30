package com.oyo.rest.restfulwebservices.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oyo.rest.restfulwebservices.model.user.User;

@Component
public class UserService {
	
	private static List<User> users = new ArrayList<User>();
	
	private static int userCount = 4;
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Budi", new Date()));
		users.add(new User(3, "Charlie", new Date()));
		users.add(new User(4, "Doyok", new Date()));
	}

	public List<User> findAll(){
		return users;
	}
	
	public User userFindById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
		
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		
		users.add(user);
		return user;
	}
	
	public User delete(int id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			if(user.getId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}
}
