package com.sda.booking.core.service;

import com.sda.booking.commons.SendEmail;
import com.sda.booking.core.entity.Availability;
import com.sda.booking.core.entity.Booking;
import com.sda.booking.core.enums.RoomType;
import com.sda.booking.core.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.aspectj.runtime.internal.Conversions.longValue;

@Service("bookingService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AvailabilityService availabilityService;

    private SendEmail sendEmail = new SendEmail();

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public void sendBookingMail(Booking booking) {

        List<Availability> allAvailabilities = availabilityService.
                findAvailabilitiesByFromDateLessThanEqualAndToDateGreaterThanEqual(booking.getCheckIn(),booking.getCheckOut());
        boolean isFound = false;
        String message = null;
        String subject = "Room reservation for " + booking.getClient().getName();;
        String eMail = booking.getClient().geteMail();;
        for(Availability a : allAvailabilities){
            if (a.getProperty().equals(booking.getProperty())){
                if (a.getFromDate().compareTo(booking.getCheckIn())< 0 && a.getToDate().compareTo(booking.getCheckOut()) > 0){
                message = "Dear " + booking.getClient().getName() + "\n" + "Thank you for choosing "
                        + booking.getProperty().getName() + ". According to your order, we make a reservation for you as following:"
                        + "\n" + "-" + booking.getNumberOfRooms() + " room(s) "
                        + "\n" + booking.getRoomType()
                        + "\n" + " check-in date: " + booking.getCheckIn()
                        + "\n" + " check-out date: " + booking.getCheckOut()
                        + "\n" + "price: " + getIntervalBetweenTwoDates(booking.getCheckIn(), booking.getCheckOut()) *
                        getRoomPrice(a, booking) + " RON."
                        + "\n" + " We are looking forward to have you our guest!";
                isFound = true;
                }
            }
        }
        if (isFound){
            sendEmail.sendEmail(message,eMail,subject);
            availabilityService.availabilitiesAfterBooking(booking);
        } else {
            String nonAvailabilityMessage = "Sorry, but we don't have available rooms in the period you chose.";
            sendEmail.sendEmail(nonAvailabilityMessage,eMail,subject);
        }
    }

    public Long getIntervalBetweenTwoDates(Date firstDate, Date secondDate){

        Long diff = secondDate.getTime() - firstDate.getTime();
        Long diffDays = diff / (60 * 60 * 1000 * 24);
        return  diffDays;

    }

    public Long getRoomPrice(Availability availability, Booking booking){

        BigDecimal price = null;
        if(booking.getRoomType().equals( String.valueOf(RoomType.SINGLE))){
            price = availability.getPriceSingle();
        }else{
            price = availability.getPriceDouble();
        }
        return longValue(price);
    }
}
