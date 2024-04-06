package com.project.service.UserService.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Integer ratingId;
    private Integer userId;
    private Integer hotelId;
    private Integer rating;
    private Hotel hotel;
}
