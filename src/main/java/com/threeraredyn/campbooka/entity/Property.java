package com.threeraredyn.campbooka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyName;
    private int noOfSites;
    private String accomodationType;
    private double area;
    private String description;

 
    @OneToOne(fetch = FetchType.LAZY)
    private Places place;
    private double price;
    private int likePercentage;
    private int pic_id;
    private int reviews;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public double getArea() {
        return area;
    }
    public void setArea(double area) {
        this.area = area;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
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
    public Places getPlace() {
        return place;
    }
    public void setPlace(Places place) {
        this.place = place;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String descrip) {
        this.description = descrip;
    }
}
