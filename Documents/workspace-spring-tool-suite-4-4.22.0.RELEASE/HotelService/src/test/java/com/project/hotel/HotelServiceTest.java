package com.project.hotel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.project.hotel.entity.Hotel;
import com.project.hotel.repository.HotelRepository;
import com.project.hotel.serviceImpl.HotelServiceImpl;

public class HotelServiceTest {
	@Mock
	private HotelRepository repo;
	@InjectMocks
	private HotelServiceImpl service;
	
	Hotel hotel;
	@BeforeEach
	public void setUp() {
		hotel=new Hotel("7cd0780f-4313-4ca6-9de8-0fc6a8734a1b", "Blue Saphire", "Greater Noida West", "I am a Java Developer");
	}
	@Test
	public void save() {
//		when(repo.save(hotel)).thenReturn(hotel);
		Hotel result=service.saveHotel(hotel);
		assertEquals("Created", hotel);
	}
	

}
