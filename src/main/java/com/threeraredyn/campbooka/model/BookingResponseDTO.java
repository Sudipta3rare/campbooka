package com.threeraredyn.campbooka.model;

import java.util.Date;

public class BookingResponseDTO {
    
    private String imageUrl;
    private String propertyName;
    private String bookingDate;
    private String camperName;
    private double payableAmount;

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getPropertyName() {
        return propertyName;
    }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    public String getCamperName() {
        return camperName;
    }
    public void setCamperName(String camperName) {
        this.camperName = camperName;
    }
    public double getPayableAmount() {
        return payableAmount;
    }
    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }
  
}
