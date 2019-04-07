package com.sda.booking.core.service;

import com.sda.booking.core.entity.Client;
import com.sda.booking.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("clientService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

}
