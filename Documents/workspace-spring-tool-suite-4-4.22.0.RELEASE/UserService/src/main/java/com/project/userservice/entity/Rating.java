package com.project.userservice.entity;



import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	private String ratingId;
	
	private String userId;
	
	private String hotelId;
	
	private int rating;
	 
	private String feedback;
	
	@Transient
	private Hotel hotel;

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", userId=" + userId + ", hotelId=" + hotelId + ", rating=" + rating
				+ ", feedback=" + feedback + ", hotel=" + hotel + "]";
	}
	
	
}
