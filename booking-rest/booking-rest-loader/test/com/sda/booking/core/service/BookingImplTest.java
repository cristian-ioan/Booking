package com.sda.booking.core.service;

import com.sda.booking.core.entity.Booking;
import com.sda.booking.core.entity.Client;
import com.sda.booking.core.entity.Property;
import com.sda.booking.core.enums.RoomType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class BookingImplTest {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PropertyService propertyService;

    @Test
    @Rollback(false)
    public void createBookingTest() {

        Booking booking = new Booking();

        booking.setClient(clientService.getById(1L));
        booking.setProperty(propertyService.getById(1L));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 12);
        Date date = cal.getTime();
        booking.setBookingDate(date);
        cal.set(2019,04,05);
        booking.setCheckIn(date);
        cal.set(2019,04,07);
        booking.setCheckOut(date);
        booking.setNumberOfRooms(1);
        booking.setRoomType(String.valueOf(RoomType.DOUBLE));
        booking.setNumberOfPersons(2);

        bookingService.createBooking(booking);


    }
}
