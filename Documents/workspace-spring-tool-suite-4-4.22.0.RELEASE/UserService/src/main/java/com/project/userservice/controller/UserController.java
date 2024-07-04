package com.project.userservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.userservice.entity.HotelAndUserAndRating;
import com.project.userservice.entity.Rating;
import com.project.userservice.entity.User;
import com.project.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	 @Autowired
	  private RestTemplate restTemplate;
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User user1=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	private ResponseEntity<User> ResponseEntity(String string, HttpStatus created) {
		// TODO Auto-generated method stub
		return null;
	}
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user=userService.getUsersDetail(userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser=userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable String userId ,@RequestBody User user){
		user.setUser_id(userId);
		
//		System.out.println("my users "+user);
		
		 userService.updateUser(userId, user);
		return ResponseEntity.ok(user);
	}
	
	@PatchMapping("/{userId}")
	public ResponseEntity<User> updateUserViaPatch(@PathVariable String userId ,@RequestBody User user){
		user.setUser_id(userId);
//		System.out.println("my users "+user);
		 userService.updateUserViaPatch(userId, user);
		return ResponseEntity.ok(user);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> removeById(@PathVariable String id){
		boolean b=userService.removebyId(id);
		System.out.println(b);
		return ResponseEntity.status(HttpStatus.OK).body(b);
	}
	
	
}
