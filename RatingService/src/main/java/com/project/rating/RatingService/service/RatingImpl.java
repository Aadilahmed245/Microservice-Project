package com.project.rating.RatingService.service;

import com.project.rating.RatingService.model.Rating;
import com.project.rating.RatingService.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingImpl implements IRating{
    @Autowired
    private RatingRepo ratingRepo;
    @Override
    public boolean addRating(Rating rating) {
        Rating save = ratingRepo.save(rating);
        return save!=null;
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public Rating getRating(Integer id) {
        return ratingRepo.findById(id).get();
    }

    @Override
    public List<Rating> getRatingByUserId(Integer userId) {
       return  ratingRepo.findByUserId(userId);
    }
}
