package com.sda.booking.core.service;

import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.enums.RoomType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class AvailabilityImplTest {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private PropertyService propertyService;

    @Test
    @Rollback(false)
    public void createAvailabilityTest() {

        Availability availability = new Availability();

        availability.setProperty(propertyService.getById(2L));
        availability.setRoomName("Room no 1");

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 10);
        Date fromDate = cal.getTime();
        availability.setFromDate(fromDate);

        cal.set(2019,03,20);
        Date toDate = cal.getTime();
        availability.setToDate(toDate);

        availability.setRoomType(String.valueOf(RoomType.SINGLE));
        availability.setPriceDouble(new BigDecimal(200));
        availability.setPriceSingle(new BigDecimal(120));

        availabilityService.createAvailability(availability);

        Assert.assertNotNull(availability);
    }

    @Test
    public void getByIdTest(){
        Availability availability = availabilityService.getById(1L);
        Assert.assertEquals("SINGLE", String.valueOf(availability.getRoomType()));
    }

    @Test
    @Rollback(false)
    public void getAllTest(){

        List<Availability> availabilityList = availabilityService.getAll();
        Assert.assertEquals(1,availabilityList.size());

    }

    @Test
    @Rollback(false)
    public void updateAvailability(){

        Availability availability = availabilityService.getById(1L);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 15);
        Date date = cal.getTime();
        availability.setToDate(date);
        availabilityService.updateAvailability(availability);

        Availability availability1 = availabilityService.getById(1L);

        Assert.assertEquals(date, availability1.getToDate());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteAvailability(){

        List<Availability> availabilityList = availabilityService.getAll();
        int size = availabilityList.size();
        availabilityService.deleteAvailability(availabilityService.getById(1L));
        List<Availability> availabilityList1 = availabilityService.getAll();
        Assert.assertEquals(size - 1, availabilityList1.size());
    }
}
