package com.project.hotel.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.entity.Hotel;
import com.project.hotel.exceptionHandler.ResourceNotfoundexception;
import com.project.hotel.repository.HotelRepository;
import com.project.hotel.service.hotelService;
@Service
public class HotelServiceImpl implements hotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	public Hotel saveHotel(Hotel hotel){
		String randomId= UUID.randomUUID().toString();
		hotel.setId(randomId);
		return hotelRepository.save(hotel);
	}
	
	public List<Hotel> getHotel() {
		return hotelRepository.findAll();
	}
	
	public Hotel getById(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotfoundexception("hotel with given id not found"));
	}
	public boolean removeByHotelId(String hotelId) {
		Hotel hotel= hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotfoundexception("hotel with given id not found"));
		if(hotel!=null) {
		 hotelRepository.deleteById(hotelId);
	}
		return true;
	}
	
	public Hotel updateById(Hotel hotel) {
		Hotel hotel1= hotelRepository.findById(hotel.getId()).orElseThrow(()->new ResourceNotfoundexception("hotel with given id not found"));
		if(hotel1!=null) {
			hotelRepository.save(hotel1);
		}
		return hotel1;
	}
	
	
}
