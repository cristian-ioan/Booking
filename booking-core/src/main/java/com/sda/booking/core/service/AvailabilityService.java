package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.entity.Booking;
import java.util.Date;
import java.util.List;

public interface AvailabilityService {

    Availability getById(Long id);
    List<Availability> getAll();
    Availability createAvailability(Availability availability);
    Availability updateAvailability(Availability availability);
    void deleteAvailability(Availability availability);
    List<Availability> findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(Date fromDate, Date toDate);
    void availabilitiesAfterBooking(Booking booking);
}
