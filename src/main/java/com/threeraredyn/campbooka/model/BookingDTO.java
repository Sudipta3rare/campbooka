package com.threeraredyn.campbooka.model;

import java.util.Date;

public class BookingDTO {
    
    private Long camperId;
    private Long propertyId;
    private Date startDate;
    private Date endDate;
    private int noOfAdults;
    private int noOfChildren;
    private int noOfVehicles;

    public Long getCamperId() {
        return camperId;
    }
    public void setCamperId(Long camperId) {
        this.camperId = camperId;
    }
    public Long getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
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
}
