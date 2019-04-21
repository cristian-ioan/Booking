package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;
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
    public List<Availability> findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(Date fromDate, Date toDate) {
        return availabilityRepository.findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(fromDate, toDate);
    }

    @Override
    @Transactional
    public void availabilitiesAfterBooking(Booking booking) {
        Date fromDate = booking.getCheckIn();
        Date toDate = booking.getCheckOut();
        List<Availability> availabilitiesBooked = availabilityRepository.findAvailabilitiesByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate,toDate);
        if(availabilitiesBooked.size()==0){
            System.out.println("No availability!");
        }else{
            for(int i = 0; i < availabilitiesBooked.size(); i++){
                if(availabilitiesBooked.get(i).getProperty().equals(booking.getProperty().getId())){
                    boolean isFound = false;
                    if(availabilitiesBooked.get(i).getFromDate().compareTo(fromDate) < 0){
                        Availability currentAvailability = availabilitiesBooked.get(i);
                        Availability newAvailability = new Availability();
                        newAvailability.setProperty(currentAvailability.getProperty());
                        newAvailability.setFromDate(currentAvailability.getFromDate());
                        newAvailability.setToDate(fromDate);
                        newAvailability.setPriceSingle(currentAvailability.getPriceSingle());
                        newAvailability.setPriceDouble(currentAvailability.getPriceDouble());
                        newAvailability.setRoomType(currentAvailability.getRoomType());
                        newAvailability.setRoomName(currentAvailability.getRoomName());
                        availabilityRepository.save(newAvailability);
                        isFound = true;
                    }
                    if(availabilitiesBooked.get(i).getFromDate().compareTo(fromDate) > 0){
                        Availability currentAvailability = availabilitiesBooked.get(i);
                        Availability newAvailability = new Availability();
                        newAvailability.setProperty(currentAvailability.getProperty());
                        newAvailability.setFromDate(toDate);
                        newAvailability.setToDate(currentAvailability.getToDate());
                        newAvailability.setPriceSingle(currentAvailability.getPriceSingle());
                        newAvailability.setPriceDouble(currentAvailability.getPriceDouble());
                        newAvailability.setRoomType(currentAvailability.getRoomType());
                        newAvailability.setRoomName(currentAvailability.getRoomName());
                        availabilityRepository.save(newAvailability);
                        isFound = true;
                    }
                    if(isFound) {
                        availabilityRepository.delete(availabilitiesBooked.get(i));
                    }
                }
            }
        }
    }
}
