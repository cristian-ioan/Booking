package com.sda.booking.core.service;

import com.sda.booking.core.entity.Payment;
import com.sda.booking.core.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("paymentService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }
}
