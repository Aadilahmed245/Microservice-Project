package com.project.service.UserService.service;

//import com.project.service.UserService.external.services.HotelService;
import com.project.service.UserService.model.Hotel;
import com.project.service.UserService.model.Rating;
import com.project.service.UserService.model.Users;
import com.project.service.UserService.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImpl implements IUser{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserImpl.class);
    @Override
    public boolean saveUser(Users user) {
        Users save = userRepo.save(user);
        return save!=null;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Users findById(Integer id) {
       Users user= userRepo.findById(id).get();
       logger.info("user info={}, userId={}",user,user.getUserId());

        // getForObject return only object
        Rating[]  ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/get-rating-by-userid?id="+user.getUserId(), Rating[].class);
        logger.info("---------------------");

        List<Rating> ratingListOfUser= Arrays.stream(ratingOfUser).collect(Collectors.toList());
        logger.info("{}",ratingListOfUser);

        // getForEntity() return wholse response entitiy class

        List<Rating> ratingsList = ratingListOfUser.stream().map(rating -> {

                    ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://localhost:8082/hotels/get-hotel-by-id?id="+rating.getHotelId(),Hotel.class);
                    Hotel hotel = responseEntity.getBody();
                    logger.info("{}",responseEntity.getStatusCode());
                    rating.setHotel(hotel);

                    return  rating;
                }).collect(Collectors.toList());
        user.setRatings(ratingsList);
        return user;
    }
}
