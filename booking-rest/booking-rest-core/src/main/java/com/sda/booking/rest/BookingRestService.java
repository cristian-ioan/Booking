package com.sda.booking.rest;

import com.sda.booking.core.entity.Booking;
import com.sda.booking.core.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/booking")
public class BookingRestService {

    @Autowired
    private BookingService bookingService;

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Booking getById(@PathParam("id") long id){
        return bookingService.getById(id);
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getAll(){
        return bookingService.getAll();
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Booking createBooking(Booking booking){
        return bookingService.createBooking(booking);
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Booking updateBooking(Booking booking){
        return bookingService.updateBooking(booking);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteBooking(@QueryParam("bookingId") long id){
        Booking booking = bookingService.getById(id);
        bookingService.deleteBooking(booking);
    }
}
