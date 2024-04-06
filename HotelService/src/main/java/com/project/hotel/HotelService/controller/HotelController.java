package com.project.hotel.HotelService.controller;

import com.project.hotel.HotelService.model.Hotel;
import com.project.hotel.HotelService.service.IHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private IHotel iHotel;
    @PostMapping("/save-hotel")
    public ResponseEntity<String> saveHotel(@RequestBody Hotel hotel)
    {
       boolean saved=  iHotel.saveHotel(hotel);
       if(saved)
       {
           return ResponseEntity.status(HttpStatus.OK).body("Hotel saved Successfully");
       }
       else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hotel not saved Successfully !!!");
       }
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        List<Hotel> hotelList=  iHotel.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(hotelList);
    }

    @GetMapping("/get-hotel-by-id")
    public ResponseEntity<Object> getHotel(@RequestParam Integer id)
    {
        Hotel hotel=  iHotel.getHotel(id);
        if(hotel!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(hotel);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("hotel not found by the given id!!!");
        }
    }


}
