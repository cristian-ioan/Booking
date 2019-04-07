package com.sda.booking.core.service;

import com.sda.booking.core.entity.Host;
import com.sda.booking.core.entity.Property;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

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
        property.setName("Hotel Faleza");
        property.seteMail("faleza@gmail.com");
        property.setPhone("0712365987");
        property.setAddress("Mamaia");

        property.setHost(hostService.getById(2L));
        propertyService.createProperty(property);
        Assert.assertNotNull(property);
    }

}
