package com.sda.booking.core.service;

import com.sda.booking.core.entity.Client;

import java.util.List;

public interface ClientService {

    Client getById(Long id);
    Client getClientByName(String name);
    List<Client> getAll();
    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Client client);

}
