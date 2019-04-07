package com.sda.booking.core.service;

import com.sda.booking.core.entity.Property;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PropertyImplTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private HostService hostService;

    @Test
    @Rollback(false)
    @Transactional
    public void createPropertyTest() {
        Property property = new Property();
        property.setName("Ruina SRL");
        property.seteMail("ruinasrl@gmail.com");
        property.setPhone("0754265987");
        property.setAddress("Brasov");

        property.setHost(hostService.getById(1L));
        propertyService.createProperty(property);
        Assert.assertNotNull(property);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getPropertyByIdTest() {
        Property property = propertyService.getById(1L);
        Assert.assertEquals("Brasov", property.getAddress());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllProportiesTest() {

        Property property = new Property();
        property.setName("Albinuta SRL");
        property.seteMail("albinutasrl@gmail.com");
        property.setPhone("0798258412");
        property.setAddress("Valea Adanca");
        property.setHost(hostService.getById(3L));
        propertyService.createProperty(property);

        List<Property> properties = propertyService.getAll();
        Assert.assertEquals(2, properties.size());
    }

    @Test
    @Rollback(false)
    public void updatePropertyTest(){

        Property property = propertyService.getById(1L);
        property.setName("Dan Negrutu");
        property.seteMail("dan.negrutu@gmail.com");
        property.setPhone("0799215478");
        property.setHost(hostService.getById(3L));
        Property expectedProperty = propertyService.updateProperty(property);
        Property actualProperty = propertyService.getById(1L);
        Assert.assertEquals(expectedProperty, actualProperty);

    }

    @Test
    @Rollback(false)
    public void deletePropertyTest(){

        List<Property> properties = propertyService.getAll();
        int size = properties.size();
        Property property = propertyService.getById(2L);
        propertyService.deleteProperty(property);
        properties = propertyService.getAll();
        Assert.assertEquals(size - 1, properties.size());

    }

}
