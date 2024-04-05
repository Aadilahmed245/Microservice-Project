package com.project.hotel.HotelService.service;

import com.project.hotel.HotelService.model.Hotel;

import java.util.List;

public interface IHotel {


    boolean saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(Integer id);
}
