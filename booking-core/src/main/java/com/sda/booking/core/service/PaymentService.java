package com.sda.booking.core.service;

import com.sda.booking.core.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment getById(Long id);
    List<Payment> getAll();
    Payment createPayment(Payment payment);
    Payment updatePayment(Payment payment);
    void deletePayment(Payment payment);
}
