package com.threeraredyn.campbooka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {
    
    @Id
    private long id;
    private String propertyName;
    private int noOfSites;
    private String accomodationType;
    private float area;

    @OneToOne(fetch = FetchType.LAZY)
    private Place place;
    private float price;
    private int likePercentage;
    private int pic_id;
    private int reviews;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPropertyName() {
        return propertyName;
    }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public int getNoOfSites() {
        return noOfSites;
    }
    public void setNoOfSites(int noOfSites) {
        this.noOfSites = noOfSites;
    }
    public String getAccomodationType() {
        return accomodationType;
    }
    public void setAccomodationType(String accomodationType) {
        this.accomodationType = accomodationType;
    }
    public float getArea() {
        return area;
    }
    public void setArea(float area) {
        this.area = area;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getLikePercentage() {
        return likePercentage;
    }
    public void setLikePercentage(int likePercentage) {
        this.likePercentage = likePercentage;
    }
    public int getPic_id() {
        return pic_id;
    }
    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }
    public int getReviews() {
        return reviews;
    }
    public void setReviews(int reviews) {
        this.reviews = reviews;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
}
