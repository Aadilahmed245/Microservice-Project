package com.project.rating.RatingService.repository;

import com.project.rating.RatingService.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating,Integer> {
    List<Rating> findByUserId(Integer userId);
}
