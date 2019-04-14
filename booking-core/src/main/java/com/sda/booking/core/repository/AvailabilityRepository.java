package com.sda.booking.core.repository;

import com.sda.booking.core.base.EntityRepository;
import com.sda.booking.core.entity.Availability;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends EntityRepository<Availability> {
    List<Availability> findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(LocalDate fromDate,
                                                                                          LocalDate toDate);

    public boolean existsAvailabilityByFromDateAndToDate(LocalDate fromDate, LocalDate toDate);
}


