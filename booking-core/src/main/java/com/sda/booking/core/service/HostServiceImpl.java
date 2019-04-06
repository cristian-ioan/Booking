package com.sda.booking.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hosttService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class HostServiceImpl implements HostService {
}
