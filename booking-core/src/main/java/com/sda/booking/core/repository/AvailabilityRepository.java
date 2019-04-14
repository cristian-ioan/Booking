package com.sda.booking.core.repository;

import com.sda.booking.core.base.EntityRepository;
import com.sda.booking.core.entity.Availability;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends EntityRepository<Availability> {

    List<Availability> findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate,
                                                                                          Date toDate);
    boolean existsAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate);

    List<Availability> getAvailabilitiesByFromDateEndingWithAndToDateIsAfter(Date fromDate, Date toDate);

}


