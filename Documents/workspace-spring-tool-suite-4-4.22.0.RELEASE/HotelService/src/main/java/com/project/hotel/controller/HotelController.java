package com.project.hotel.controller;

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

import com.project.hotel.entity.Hotel;
import com.project.hotel.serviceImpl.HotelServiceImpl;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private com.project.hotel.service.hotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllhotel() {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelByid(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> removeById(@PathVariable String id){
		Boolean b= hotelService.removeByHotelId(id);
	 return ResponseEntity.status(HttpStatus.OK).body(b);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateById(@PathVariable String id,@RequestBody Hotel hotel){
		hotel.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateById(hotel));
	}
}
