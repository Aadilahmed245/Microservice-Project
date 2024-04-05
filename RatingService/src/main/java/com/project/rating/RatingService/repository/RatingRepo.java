package com.project.rating.RatingService.repository;

import com.project.rating.RatingService.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating,Integer> {
}
