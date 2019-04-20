package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AvailabilityService {

    Availability getById(Long id);
    List<Availability> getAll();
    Availability createAvailability(Availability availability);
    Availability updateAvailability(Availability availability);
    void deleteAvailability(Availability availability);
    List<Availability> findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate);
    boolean existsAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate);
    List<Availability> getAvailabilitiesByFromDateEndingWithAndToDateIsAfter(Date fromDate, Date toDate);

}
