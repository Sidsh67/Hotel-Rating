package com.project.ratingService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rating {

	@Id
	private String ratingId;
	
	private String userId;
	
	private String hotelId;
	
	private int rating;
	
	private String feedback;
}
