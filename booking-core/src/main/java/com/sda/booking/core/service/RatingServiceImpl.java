package com.sda.booking.core.service;

import com.sda.booking.core.entity.Rating;
import com.sda.booking.core.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ratingService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating getById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

}
