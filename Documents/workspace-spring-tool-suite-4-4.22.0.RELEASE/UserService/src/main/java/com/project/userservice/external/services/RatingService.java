package com.project.userservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.userservice.entity.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
	
	@GetMapping("/rating/user/{userId}")
	List<Rating> getRatingById(@PathVariable("userId") String userId);
	
	@PostMapping("/rating")
	Rating create(Rating value);

	@PutMapping("/rating/{id}")
	Rating update(@PathVariable("id") String id, Rating rating);
	
	@DeleteMapping(value ="/rating/{id}")
	String deleteBy( @PathVariable("id") String id);
	
	@PatchMapping(value="/rating/{id}")
	Rating updateViaPatch( @RequestBody Rating rating , @PathVariable("id") String id);
}
