package com.project.hotel.service;

import java.util.List;

import com.project.hotel.entity.Hotel;

public interface hotelService {

	public Hotel saveHotel(Hotel hotel);
	
	public List<Hotel> getHotel();
	
	public Hotel getById(String hotelId);
	
	public boolean removeByHotelId(String hotelId);
	
	public Hotel updateById(Hotel hotel);
	
}
