package com.sda.booking.core.service;
import com.sda.booking.core.entity.Client;
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
public class ClientImplTest {

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    @Transactional
    public void createClientTest() {
        Client client = new Client();
        client.setName("Bogdan Bogdanel");
        client.setPhone("0726789456");
        client.seteMail("bogdan.bogdanel@gmail.com");
        clientService.createClient(client);
        Assert.assertNotNull(client);
    }

}
