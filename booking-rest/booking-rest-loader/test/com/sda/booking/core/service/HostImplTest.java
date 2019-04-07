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
        host.setName("Dan Negru");
        host.seteMail("dan.negrul@gmail.com");
        hostService.createHost(host);
        Assert.assertNotNull(host);
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

        Host host1 = new Host();
        host1.setName("Esca Andreea");
        host1.seteMail("esca.andreea@gmail.com");
        hostService.createHost(host1);

        Host host2 = new Host();
        host2.setName("Grigore Lese");
        host2.seteMail("lese.grigore@gmail.com");
        hostService.createHost(host2);

        List<Host> hosts = hostService.getAll();
        Assert.assertEquals(3, hosts.size());
    }


    @Test
    @Rollback(false)
    public void updateHostTest(){

        Host host = hostService.getById(3L);
        host.setName("Dan Otil");
        host.seteMail("dan.otil@gmail.com");
        Host expectedHost = hostService.updateHost(host);
        Host actualHost = hostService.getById(3L);
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
