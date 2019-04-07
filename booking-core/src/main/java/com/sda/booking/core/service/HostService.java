package com.sda.booking.core.service;

import com.sda.booking.core.entity.Host;

import java.util.List;

public interface HostService {

    Host getById(Long id);
    List<Host> getAll();
    Host createHost(Host host);
    Host updateHost(Host host);
    void deleteHost(Host host);

}
