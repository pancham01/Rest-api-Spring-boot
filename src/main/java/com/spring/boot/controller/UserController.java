package com.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dao.UserRepo;
import com.spring.boot.model.User;
import com.spring.boot.model.UserRestUriConstant;

@RestController
public class UserController {
	@Autowired
	private UserRepo dao;
	
	private static Map<Integer, User> map = new HashMap<Integer, User>();

	static {
		map.put(1, new User(1, "Mukul", "M1234"));
		map.put(2, new User(2, "Nakul", "N1234"));
		map.put(3, new User(3, "Praneet", "M1234"));
		map.put(4, new User(4, "Ashish", "M1234"));
	}
	
	@GetMapping(UserRestUriConstant.TEST)
	public User greet() {
		System.out.println("UserController.greet()");
		return new User(1, "Vikas", "v12345");
	}

	@GetMapping(UserRestUriConstant.GET_USER)
	public User getUserById(@PathVariable("id") int id) {
		return map.get(id);
	}
	
	@GetMapping(UserRestUriConstant.GET_ALL_USERS)
	public Map<Integer,User> getAllUsers() {
		return map;
	}

	@PostMapping(UserRestUriConstant.CREATE_USER)
	public User createUser(@RequestBody User user) {
		System.out.println(user);
		dao.save(user);
		return user;
	}

	@PutMapping(UserRestUriConstant.UPDATE_USER)
	public User updateUser(@RequestBody User user) {
		User user2 = map.get(user.getId());
		if (user2 != null) {
			user2.setUsername(user.getUsername());
			user2.setPass(user.getPass());
			map.put(user2.getId(), user2);
		}
		System.out.println(user2);
		return user2;
	}
	
	

	@DeleteMapping(UserRestUriConstant.DELETE_USER)
	public User deleteUserById(@PathVariable("id") int id) {
		return map.remove(id);
	}

}
