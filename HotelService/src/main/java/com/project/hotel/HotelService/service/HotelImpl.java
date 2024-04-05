package com.project.hotel.HotelService.service;

import com.project.hotel.HotelService.model.Hotel;
import com.project.hotel.HotelService.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelImpl implements IHotel{
    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public boolean saveHotel(Hotel hotel) {
        Hotel save = hotelRepo.save(hotel);
        return save!=null;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotel(Integer id) {
        return hotelRepo.findById(id).get();
    }
}
