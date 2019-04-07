package com.sda.booking.core.service;

import com.sda.booking.core.entity.Property;

import java.util.List;

public interface PropertyService {

    Property getById(Long id);
    List<Property> getAll();
    Property createProperty(Property property);
    Property updateProperty(Property property);
    void deleteProperty(Property property);

}
