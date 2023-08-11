package com.threeraredyn.campbooka.entity;

import java.util.Date;

import com.threeraredyn.campbooka.enumeration.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User camper;

    @OneToOne
    private Property property;
    
    private Date startDate;
    private Date endDate;
    private int noOfAdults;
    private int noOfChildren;
    private int noOfVehicles;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getCamper() {
        return camper;
    }
    public void setCamper(User camper) {
        this.camper = camper;
    }
    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getNoOfAdults() {
        return noOfAdults;
    }
    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }
    public int getNoOfChildren() {
        return noOfChildren;
    }
    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }
    public int getNoOfVehicles() {
        return noOfVehicles;
    }
    public void setNoOfVehicles(int noOfVehicles) {
        this.noOfVehicles = noOfVehicles;
    }
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
}
