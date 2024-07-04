package com.project.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.userservice.entity.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HoterService {

	@GetMapping("/hotel/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
	@PostMapping("/hotel")
	Hotel saveHotel( Hotel hotel);
}
