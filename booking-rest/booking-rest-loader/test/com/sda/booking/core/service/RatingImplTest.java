package com.sda.booking.core.service;

import com.sda.booking.core.entity.Rating;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class RatingImplTest {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PropertyService propertyService;

    @Test
    @Transactional
    @Rollback(false)
    public void getRatingByIdTest() {
        Rating rating = ratingService.getById(1L);
        Assert.assertEquals("sunteti praf", rating.getComment());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllRatingsTest() {

        Rating rating = new Rating();
        rating.setClient(clientService.getById(11L));
        rating.setComment("sunteti asa si asa");
        rating.setProperty(propertyService.getById(1L));
        rating.setRating(2);
        ratingService.createRating(rating);

        List<Rating> ratings = ratingService.getAll();
        Assert.assertEquals(2, ratings.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void createRatingTest() {
        Rating rating = new Rating();
        rating.setClient(clientService.getById(3L));
        rating.setComment("sunteti praf");
        rating.setProperty(propertyService.getById(1L));
        rating.setRating(1);

        ratingService.createRating(rating);
        Assert.assertNotNull(rating);
    }

    @Test
    @Rollback(false)
    public void updateRatingTest(){

        Rating rating = ratingService.getById(2L);
        rating.setClient(clientService.getById(3L));
        rating.setComment("comsi comsa");
        rating.setProperty(propertyService.getById(1L));
        Rating expectedRating = ratingService.updateRating(rating);
        Rating actualRating = ratingService.getById(2L);
        Assert.assertEquals(expectedRating, actualRating);

    }

    @Test
    @Rollback(false)
    public void deleteRatingTest(){

        List<Rating> ratings = ratingService.getAll();
        int size = ratings.size();
        Rating rating = ratingService.getById(2L);
        ratingService.deleteRating(rating);
        ratings = ratingService.getAll();
        Assert.assertEquals(size - 1, ratings.size());

    }

}
