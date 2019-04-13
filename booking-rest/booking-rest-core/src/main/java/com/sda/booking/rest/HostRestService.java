package com.sda.booking.rest;

import com.sda.booking.core.entity.Host;
import com.sda.booking.core.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/host")
public class HostRestService {

    @Autowired
    private HostService hostService;

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Host getById(@PathParam("id") long id){
        return hostService.getById(id);
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Host> getAll(){
        return hostService.getAll();
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Host createHost(Host host){
        return hostService.createHost(host);
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Host updateHost(Host host){
        return hostService.updateHost(host);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteHost(@QueryParam("hostId") long id){
        Host host = hostService.getById(id);
        hostService.deleteHost(host);
    }
}
