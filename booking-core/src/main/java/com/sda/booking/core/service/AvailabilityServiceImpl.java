package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
