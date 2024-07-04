package com.project.hotel.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class Staff_Service {

	@GetMapping
	public ResponseEntity<List<String>> staffDetail(){
		List<String> list= Arrays.asList("Arya","Nitya","Riya","karan","Aditya");
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
