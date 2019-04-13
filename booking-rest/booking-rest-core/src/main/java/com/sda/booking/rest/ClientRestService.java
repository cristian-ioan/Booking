package com.sda.booking.rest;

import com.sda.booking.core.entity.Client;
import com.sda.booking.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/client")
public class ClientRestService {

    @Autowired
    private ClientService clientService;

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Client getById(@PathParam("id") long id){
        return clientService.getById(id);
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getAll(){
        return clientService.getAll();
    }

    @Path("/get-name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Client getById(@PathParam("name") String name){
        return clientService.getClientByName(name);
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Client createClient(Client client){
        return clientService.createClient(client);
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Client updateClient(Client client){
        return clientService.updateClient(client);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteClient(@QueryParam("clientId") long id){
        Client client = clientService.getById(id);
        clientService.deleteClient(client);
    }

}
