package com.sda.booking.core.service;

import com.sda.booking.core.entity.Host;
import com.sda.booking.core.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hostService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository hostRepository;

    @Override
    public Host getById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> getAll() {
        return hostRepository.findAll();
    }

    @Override
    @Transactional
    public Host createHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    @Transactional
    public Host updateHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    @Transactional
    public void deleteHost(Host host) {
        hostRepository.delete(host);
    }
}

