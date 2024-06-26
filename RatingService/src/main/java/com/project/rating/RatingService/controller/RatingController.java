package com.project.rating.RatingService.controller;

import com.project.rating.RatingService.model.Rating;
import com.project.rating.RatingService.service.IRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private IRating iRating;
    @PostMapping("/save-rating")
    public ResponseEntity<String> addRating(@RequestBody Rating rating)
    {
        boolean saved =   iRating.addRating(rating);
        if(saved)
        {
            return ResponseEntity.status(HttpStatus.OK).body("Rating saved successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rating not saved successfully !!!");
        }
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings()
    {
        List<Rating> ratings =   iRating.getAllRatings();
        return ResponseEntity.status(HttpStatus.OK).body(ratings);
    }
    @GetMapping("get-rating-by-userid")
    public ResponseEntity<List<Rating>> getRatingByUserId(@RequestParam Integer id)
    {
        List<Rating> rating = iRating.getRatingByUserId(id);
        System.out.println(rating);
        if(rating!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(rating);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rating);
        }
    }
    @GetMapping("/get-rating")
    public ResponseEntity<Rating> getAllRatings(@RequestParam Integer id)
    {
        Rating rating =   iRating.getRating(id);
        return ResponseEntity.status(HttpStatus.OK).body(rating);
    }
}
