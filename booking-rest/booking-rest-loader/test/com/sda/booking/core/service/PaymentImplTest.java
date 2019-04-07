package com.sda.booking.core.service;

import com.sda.booking.core.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PaymentImplTest {

    @Autowired
    private PaymentService paymentService;
    private ClientService clientService;
    private BookingService bookingService;
    private PropertyService propertyService;
    private HostService hostService;

    @Test
    @Rollback(false)
    public void createPaymentTest() {

        Payment payment = new Payment();
        payment.setAmount(new BigDecimal(10));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 12);
        Date date = cal.getTime();
        payment.setPaymentDate(date);

        Assert.assertEquals(new BigDecimal(10), payment.getAmount());
        Booking booking = new Booking();

        Client client = new Client();
        client.setName("Ion Popescu");
        client.seteMail("ionpopescu@gmail.com");
        client.setPhone("0754132145");
        clientService.createClient(client);
        Long clientId = client.getId();

        booking.setId(clientId);
        cal.set(2019,04,15);
        date = cal.getTime();
        booking.setBookingDate(date);
        cal.set(2019,05,15);
        date = cal.getTime();
        booking.setCheckIn(date);
        cal.set(2019,05,20);
        date = cal.getTime();
        booking.setCheckOut(date);
        booking.setNumberOfPersons(2);
        booking.setNumberOfRooms(1);


        Property property = new Property();
        property.setName("Hotel Alpin");
        property.seteMail("hotelalpin.@yahoo.com");
        property.setPhone("0765432123");
        property.setAddress("Poiana Brasov");

        Host host = new Host();
        host.setName("Dan Negru");
        host.seteMail("dannegru@yahoo.com");
        hostService.createHost(host);

        Long hostId = host.getId();
        propertyService.createProperty(property);
        property.setId(hostId);
        Long propertyId = property.getId();
        booking.setId(propertyId);
        bookingService.createBooking(booking);
        Long bookingId = booking.getId();

        payment.setId(bookingId);
        paymentService.createPayment(payment);

        Assert.assertEquals(new BigDecimal(10), payment.getAmount());






    }
}
