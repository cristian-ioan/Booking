package com.sda.booking.rest;

import com.sda.booking.core.entity.Property;
import com.sda.booking.core.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/property")
public class PropertyRestService {

    @Autowired
    private PropertyService propertyService;

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Property getById(@PathParam("id") long id){
        return propertyService.getById(id);
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Property> getAll(){
        return propertyService.getAll();
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Property createProperty(Property property){
        return propertyService.createProperty(property);
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Property updateProperty(Property property){
        return propertyService.updateProperty(property);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteProperty(@QueryParam("ratingId") long id){
        Property property = propertyService.getById(id);
        propertyService.deleteProperty(property);
    }
}
