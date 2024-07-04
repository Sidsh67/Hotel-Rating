package com.project.userservice.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelAndUserAndRating {
	private List<Rating> rating;
	private User user;
	private List<Hotel> hotel;
	
}
