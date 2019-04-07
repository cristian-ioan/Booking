package com.sda.booking.core.service;

import com.sda.booking.core.entity.Booking;
import com.sda.booking.core.enums.RoomType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        Date dateCheck1 = cal.getTime();
        booking.setCheckIn(dateCheck1);
        cal.set(2019,04,07);
        Date dateCheckOut = cal.getTime();
        booking.setCheckOut(dateCheckOut);
        booking.setNumberOfRooms(1);
        booking.setRoomType(String.valueOf(RoomType.DOUBLE));
        booking.setNumberOfPersons(2);

        bookingService.createBooking(booking);

        Assert.assertNotNull(booking);
    }

    @Test
    public void getByIdTest(){
        Booking booking = bookingService.getById(2L);
        Assert.assertEquals("DOUBLE", String.valueOf(booking.getRoomType()));
    }

    @Test
    @Rollback(false)
    public void getAllTest(){

        List<Booking> bookingList = bookingService.getAll();
        Assert.assertEquals(2,bookingList.size());

    }

    @Test
    @Rollback(false)
    public void updateBooking(){

        Booking booking = bookingService.getById(1L);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 15);
        Date date = cal.getTime();
        booking.setCheckOut(date);
        bookingService.updateBooking(booking);

        Booking booking1 = bookingService.getById(1L);

        Assert.assertEquals(date, booking1.getCheckOut());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteBooking(){

        List<Booking> bookingList = bookingService.getAll();
        int size = bookingList.size();
        bookingService.deleteBooking(bookingService.getById(1L));
        List<Booking> bookingList1 = bookingService.getAll();
        Assert.assertEquals(size - 1, bookingList1.size());
    }
}
