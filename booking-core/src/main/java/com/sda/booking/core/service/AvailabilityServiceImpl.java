package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.entity.Booking;
import com.sda.booking.core.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("availabilityService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AvailabilityServiceImpl implements AvailabilityService {


    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public Availability getById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public List<Availability> getAll() {
        return availabilityRepository.findAll();
    }

    @Override
    @Transactional
    public Availability createAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    @Transactional
    public Availability updateAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    @Transactional
    public void deleteAvailability(Availability availability) {
        availabilityRepository.delete(availability);
    }

    @Override
    public List<Availability> findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(Date fromDate, Date toDate) {
        return availabilityRepository.findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(fromDate, toDate);
    }

    @Override
    @Transactional
    public void availabilitiesAfterBooking(Booking booking) {
        Date fromDate = booking.getCheckIn();
        Date toDate = booking.getCheckOut();
        List<Availability> availabilitiesBooked = availabilityRepository.
                findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(fromDate,toDate);
        for(Availability a: availabilitiesBooked){
            if (a.getProperty().equals(booking.getProperty())) {
                boolean isFound = false;
                if (a.getFromDate().compareTo(booking.getCheckIn()) < 0) {
                    Availability newAvailability = new Availability();
                    newAvailability.setProperty(a.getProperty());
                    newAvailability.setFromDate(a.getFromDate());
                    newAvailability.setToDate(fromDate);
                    newAvailability.setPriceSingle(a.getPriceSingle());
                    newAvailability.setPriceDouble(a.getPriceDouble());
                    newAvailability.setRoomType(a.getRoomType());
                    newAvailability.setRoomName(a.getRoomName());
                    availabilityRepository.save(newAvailability);
                    isFound = true;
                }
                if (a.getToDate().compareTo(booking.getCheckOut()) > 0) {
                    Availability newAvailability = new Availability();
                    newAvailability.setProperty(a.getProperty());
                    newAvailability.setFromDate(toDate);
                    newAvailability.setToDate(a.getToDate());
                    newAvailability.setPriceSingle(a.getPriceSingle());
                    newAvailability.setPriceDouble(a.getPriceDouble());
                    newAvailability.setRoomType(a.getRoomType());
                    newAvailability.setRoomName(a.getRoomName());
                    availabilityRepository.save(newAvailability);
                    isFound = true;
                }
                if (isFound) {
                    availabilityRepository.delete(a);
                }

            }
        }
    }
}

