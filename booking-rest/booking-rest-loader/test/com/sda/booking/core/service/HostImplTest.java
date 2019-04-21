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
import java.util.List;

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
        host.setName("Dani Otil");
        host.seteMail("dani.otil@gmail.com");
        hostService.createHost(host);
        List<Host> hosts = hostService.getAll();
        Assert.assertEquals(3, hosts.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getHostByIdTest() {
        Host host = hostService.getById(1L);
        Assert.assertEquals("Dan Negru", host.getName());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllHostsTest() {

        Host host = new Host();
        host.setName("Grigore Lese");
        host.seteMail("lese.grigore@gmail.com");
        hostService.createHost(host);

        List<Host> hosts = hostService.getAll();
        Assert.assertEquals(4, hosts.size());
    }


    @Test
    @Rollback(false)
    public void updateHostTest(){

        Host host = hostService.getById(1L);
        host.setName("Dan Negru");
        host.seteMail("dan.negru@gmail.com");
        Host expectedHost = hostService.updateHost(host);
        Host actualHost = hostService.getById(1L);
        Assert.assertEquals(expectedHost, actualHost);

    }

    @Test
    @Rollback(false)
    public void deleteHostTest(){

        List<Host> hosts = hostService.getAll();
        int size = hosts.size();
        Host host = hostService.getById(2L);
        hostService.deleteHost(host);
        hosts = hostService.getAll();
        Assert.assertEquals(size - 1, hosts.size());

    }

}
