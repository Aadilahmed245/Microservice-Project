package com.project.rating.RatingService.service;

import com.project.rating.RatingService.model.Rating;

import java.util.List;

public interface IRating {
    boolean addRating(Rating rating);

    List<Rating> getAllRatings();

    Rating getRating(Integer id);
}
