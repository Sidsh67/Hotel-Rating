package com.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	

	
}
