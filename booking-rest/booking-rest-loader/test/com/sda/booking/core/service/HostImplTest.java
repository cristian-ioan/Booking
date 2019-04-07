package com.sda.booking.core.service;

import com.sda.booking.core.entity.Host;
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
public class HostImplTest {

    @Autowired
    private HostService hostService;

    @Test
    @Rollback(false)
    @Transactional
    public void createHostTest() {
        Host host = new Host();
        host.setName("Dan Negru");
        host.seteMail("dan.negrul@gmail.com");
        hostService.createHost(host);
        Assert.assertNotNull(host);
    }
}
