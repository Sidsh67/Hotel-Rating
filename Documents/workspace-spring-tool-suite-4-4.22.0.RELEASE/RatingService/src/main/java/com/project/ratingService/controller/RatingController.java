package com.project.ratingService.controller;

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

import com.project.ratingService.entity.Rating;
import com.project.ratingService.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService service;

	@PostMapping
	public ResponseEntity<Rating> save(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(rating));
	}
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getRatings());
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.OK).body(service.getRatingByUserId(userId));
	}
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelrId(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(service.getRatingByHotelId(hotelId));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeById(@PathVariable String id){
		
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Rating> updateById(@RequestBody Rating rating,@PathVariable String id){
		rating.setRatingId(id);
		return ResponseEntity.status(HttpStatus.OK).body(service.updateById(rating));
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<Rating> updateByIdPatch(@RequestBody Rating rating, @PathVariable String id){
		rating.setRatingId(id);
		return ResponseEntity.status(HttpStatus.OK).body(service.updateByIdPatch(rating));
	}
}
