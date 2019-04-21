package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.entity.Booking;
import java.util.List;

public interface BookingService {

    Booking getById(Long id);
    List<Booking> getAll();
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(Booking booking);
    void sendBookingMail(Booking booking, Availability availability);

}
