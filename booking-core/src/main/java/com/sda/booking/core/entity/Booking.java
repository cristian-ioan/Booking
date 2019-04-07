package com.sda.booking.core.entity;

import com.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings", schema = "booking")
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_in", length = 8, nullable = false)
    private Date checkIn;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_out", length = 8, nullable = false)
    private Date checkOut;

    @Column(name = "no_of_persons", length = 4, nullable = false)
    private Integer numberOfPersons;

    @Column(name = "room_type", length = 10, nullable = false)
    private String roomType;

    @Column(name = "no_of_rooms", length = 4, nullable = false)
    private Integer numberOfRooms;

    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date", length = 8, nullable = false)
    private Date bookingDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(getCheckIn(), booking.getCheckIn()) &&
                Objects.equals(getCheckOut(), booking.getCheckOut()) &&
                Objects.equals(getNumberOfPersons(), booking.getNumberOfPersons()) &&
                Objects.equals(getRoomType(), booking.getRoomType()) &&
                Objects.equals(getNumberOfRooms(), booking.getNumberOfRooms()) &&
                Objects.equals(getBookingDate(), booking.getBookingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCheckIn(), getCheckOut(), getNumberOfPersons(), getRoomType(), getNumberOfRooms(), getBookingDate());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", numberOfPersons=" + numberOfPersons +
                ", roomType='" + roomType + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", bookingDate=" + bookingDate +
                '}';
    }
}



