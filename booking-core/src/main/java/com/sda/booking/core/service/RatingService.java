package com.sda.booking.core.service;

import com.sda.booking.core.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating getById(Long id);
    List<Rating> getAll();
    Rating createRating(Rating rating);
    Rating updateRating(Rating rating);
    void deleteRating(Rating rating);
}
