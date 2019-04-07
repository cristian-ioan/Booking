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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ClientImplTest {

    @Autowired
    private ClientService clientService;

    @Test
    @Transactional
    @Rollback(false)
    public void getClientByIdTest() {
        Client client = clientService.getById(3L);
        Assert.assertEquals("Bogdan Bogdanel", client.getName());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllClientsTest() {

        Client client1 = new Client();
        client1.setName("Madalina Georgiana");
        client1.setPhone("0726789456");
        client1.seteMail("madalina.georgina@gmail.com");
        clientService.createClient(client1);

        Client client2 = new Client();
        client2.setName("Mardare Cristina");
        client2.setPhone("0726789465");
        client2.seteMail("mardare.cristina@gmail.com");
        clientService.createClient(client2);

        List<Client> clients = clientService.getAll();
        Assert.assertEquals(3, clients.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void createClientTest() {
        Client client = new Client();
        client.setName("Radu Mazare");
        client.setPhone("0726734256");
        client.seteMail("radumazare@gmail.com");
        clientService.createClient(client);
        Assert.assertNotNull(client);
    }

    @Test
    @Rollback(false)
    public void updateClientTest(){

        Client client = clientService.getById(10L);
        client.setName("Mardare Mariana");
        client.setPhone("0754658741");
        client.seteMail("mardare.mariana@gmail.com");
        Client expectedClient = clientService.updateClient(client);
        Client actualClient = clientService.getById(10L);
        Assert.assertEquals(expectedClient, actualClient);

    }

    @Test
    @Rollback(false)
    public void deleteClientTest(){

        List<Client> clients = clientService.getAll();
        int size = clients.size();
        Client client = clientService.getById(10L);
        clientService.deleteClient(client);
        clients = clientService.getAll();
        Assert.assertEquals(size - 1, clients.size());

    }

}
