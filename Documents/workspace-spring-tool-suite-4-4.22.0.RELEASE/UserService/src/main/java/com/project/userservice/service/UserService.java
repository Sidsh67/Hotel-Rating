package com.project.userservice.service;

import java.util.List;

import com.project.userservice.entity.User;

public interface UserService {

//save user
	public User saveUser(User user);
//	public AllEntityInOne saveUser1(User user);
//Get Single User by Id 
	public User getUsersDetail(String userId);
//Get AllUser
	public List<User> getAllUser();
	
	public User updateUser(String userId, User user2);
	
	public boolean removebyId(String id);
	
	public User updateUserViaPatch(String userId, User user2);
	
}
