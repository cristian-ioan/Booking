package com.sda.booking.core.service;

import com.sda.booking.core.entity.Property;
import com.sda.booking.core.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("propertyService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property getById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    @Transactional
    public Property updateProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    @Transactional
    public void deleteProperty(Property property) {
        propertyRepository.delete(property);
    }
}
