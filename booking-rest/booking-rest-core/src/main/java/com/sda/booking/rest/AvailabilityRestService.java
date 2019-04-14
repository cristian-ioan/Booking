package com.sda.booking.rest;

import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

    @Service
    @Path("/availability")
    public class AvailabilityRestService {

        @Autowired
        private AvailabilityService availabilityService;

        @Path("/get/{id}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Availability getById(@PathParam("id") long id){
            return availabilityService.getById(id);
        }

        @Path("/get-all")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Availability> getAll(){
            return availabilityService.getAll();
        }

        @Path("/create")
        @PUT
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Availability createAvailability(Availability availability){
            return availabilityService.createAvailability(availability);
        }

        @Path("/update")
        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        @Transactional
        public Availability updateAvailability(Availability availability){
            return availabilityService.updateAvailability(availability);
        }

        @Path("/delete")
        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Transactional
        public void deleteAvailability(@QueryParam("availabilityId") long id){
            Availability availability = availabilityService.getById(id);
            availabilityService.deleteAvailability(availability);
        }

        @Path("/get-availability")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Availability> findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(
                @QueryParam("from") String stringFromDate, @QueryParam("to") String stringToDate ) throws ParseException {
            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringFromDate);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringToDate);
            return availabilityService.findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate, toDate);
        }
}
