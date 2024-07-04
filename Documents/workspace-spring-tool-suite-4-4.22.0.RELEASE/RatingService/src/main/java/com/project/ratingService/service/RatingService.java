package com.project.ratingService.service;

import java.util.List;

import com.project.ratingService.entity.Rating;

public interface RatingService {

	Rating create(Rating rating);
	
	List<Rating> getRatings();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
	
	public String removeById(String id);
	public Rating updateById(Rating rating);
	public Rating updateByIdPatch(Rating rating);
	
}
