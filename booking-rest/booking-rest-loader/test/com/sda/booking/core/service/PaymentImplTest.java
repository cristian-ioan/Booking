package com.sda.booking.core.service;

import com.sda.booking.core.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class PaymentImplTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    @Test
    @Rollback(false)
    public void createPaymentTest() {

        Payment payment = new Payment();
        payment.setAmount(new BigDecimal(10));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 12);
        Date date = cal.getTime();
        payment.setPaymentDate(date);
        payment.setBooking(bookingService.getById(2L));

        paymentService.createPayment(payment);

        Assert.assertNotNull(payment);
    }

    @Test
    public void getByIdTest(){

        Payment payment = paymentService.getById(1L);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 12);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String myDate = format.format(date);

        Assert.assertEquals(myDate,String.valueOf(payment.getPaymentDate()));

    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllTest(){

        Payment payment1 = new Payment();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 07, 10);
        Date date = cal.getTime();
        payment1.setPaymentDate(date);
        payment1.setAmount(new BigDecimal(50));
        payment1.setBooking(bookingService.getById(2L));
        payment1.setAmount(new BigDecimal(20));
        paymentService.createPayment(payment1);

        List<Payment> paymentsList = paymentService.getAll();

        Assert.assertEquals(2,paymentsList.size());

    }

    @Test
    @Rollback(false)
    public void updatePayment(){
        Payment payment = paymentService.getById(1L);
        payment.setAmount(new BigDecimal(50));
        paymentService.updatePayment(payment);
        List<Payment> paymentList = paymentService.getAll();
        Assert.assertEquals(new BigDecimal(50),paymentList.get(0).getAmount());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void deletePayment(){

        List<Payment> paymentList1 = paymentService.getAll();
        int size1 = paymentList1.size();
        Payment payment = paymentService.getById(4L);
        paymentService.deletePayment(payment);
        paymentList1 = paymentService.getAll();
        Assert.assertEquals(size1 - 1, paymentList1.size());

    }
}
