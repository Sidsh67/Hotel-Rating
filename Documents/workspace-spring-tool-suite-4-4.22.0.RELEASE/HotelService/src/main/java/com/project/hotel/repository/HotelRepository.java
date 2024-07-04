package com.project.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
