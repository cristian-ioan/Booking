package com.sda.booking.rest;

import com.sda.booking.core.entity.Rating;
import com.sda.booking.core.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/rating")
public class RatingRestService {

    @Autowired
    private RatingService ratingService;

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Rating getById(@PathParam("id") long id){
        return ratingService.getById(id);
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getAll(){
        return ratingService.getAll();
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rating createRating(Rating rating){
        return ratingService.createRating(rating);
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Rating updateRating(Rating rating){
        return ratingService.updateRating(rating);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteRating(@QueryParam("ratingId") long id){
        Rating rating = ratingService.getById(id);
        ratingService.deleteRating(rating);
    }

}
