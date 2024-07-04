package com.project.ratingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ratingService.entity.Rating;

public interface Ratingrepository extends JpaRepository<Rating, String>{

	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
