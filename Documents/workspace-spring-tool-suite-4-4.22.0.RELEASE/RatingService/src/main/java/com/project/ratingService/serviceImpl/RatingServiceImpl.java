package com.project.ratingService.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ratingService.entity.Rating;
import com.project.ratingService.repository.Ratingrepository;
import com.project.ratingService.service.RatingService;
@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private Ratingrepository repository;
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		String randomId=UUID.randomUUID().toString();
		rating.setRatingId(randomId);
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return repository.findByHotelId(hotelId);
	}
	
	public String removeById(String id) {
		Rating rating= repository.findById(id).get();
		if(rating != null) {
		repository.delete(rating);
		return "Deleted";
		}else {
			return "Data not found";
		}
		
	}
	public Rating updateById(Rating rating) {
		System.out.println("rating service   =========="+rating);
//		Rating rating2 =  repository.findById(rating.getRatingId()).get();
		 Optional<Rating> rating2 = repository.findById(rating.getRatingId());
		 
		System.out.println("+++++++++++++++++++++"+ rating);
		if(rating2 != null) {
			System.out.println("inside if ++++++"+ rating2);
			rating2.get().setRating(rating.getRating());
			rating2.get().setFeedback(rating.getFeedback());
			  return repository.save(rating2.get());
		}
		return rating;
	}
	
	public Rating updateByIdPatch(Rating rating) {
		System.out.println("rating service patch   =========="+rating);
		Rating rating2 =  repository.findById(rating.getRatingId()).get();
		
		if(rating != null) {
			
			rating2.setRating(rating.getRating());
			rating2.setFeedback(rating.getFeedback());
			
			 repository.save(rating2);
		}
		return rating;
	}
 
}
