package com.project.hotel.HotelService.repository;

import com.project.hotel.HotelService.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,Integer> {
}
