package com.project.service.UserService.external.services;

import com.project.service.UserService.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/get-hotel-by-id")
    Hotel getHotel(@RequestParam Integer id);
}
