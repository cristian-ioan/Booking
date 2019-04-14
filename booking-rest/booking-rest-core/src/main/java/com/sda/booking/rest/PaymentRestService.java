package com.sda.booking.rest;

import com.sda.booking.core.entity.Payment;
import com.sda.booking.core.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Service
@Path("/payment")
public class PaymentRestService {

    @Autowired
    private PaymentService paymentService;

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getById(@PathParam("id") long id){
        return paymentService.getById(id);
    }

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @Path("/create")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Payment createPayment(Payment payment){
        return paymentService.createPayment(payment);
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Payment updatePayment(Payment payment){
        return paymentService.updatePayment(payment);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deletePayment(@QueryParam("paymentId") long id){
        Payment payment = paymentService.getById(id);
        paymentService.deletePayment(payment);
    }
}
