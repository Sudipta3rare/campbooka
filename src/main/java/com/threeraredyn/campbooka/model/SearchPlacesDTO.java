package com.threeraredyn.campbooka.model;

public class SearchPlacesDTO {
    private  long placeId;
    private String fromDate;
    private String toDate;
    private int noOfAdults;
    private int noOfChildren;
    
    public long getPlaceId() {
        return placeId;
    }
    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
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
  
}
