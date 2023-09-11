package com.threeraredyn.campbooka.model;

public class PropertyResponseDTO {
    private Long id;
    private String propertyName;
    private String accomodationType;
    private Double area;
    private String description;
    private Double price;
    private Integer likePercentage;
    private Integer reviews;
    
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
    public String getAccomodationType() {
        return accomodationType;
    }
    public void setAccomodationType(String accomodationType) {
        this.accomodationType = accomodationType;
    }
    public Double getArea() {
        return area;
    }
    public void setArea(Double area) {
        this.area = area;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getLikePercentage() {
        return likePercentage;
    }
    public void setLikePercentage(Integer likePercentage) {
        this.likePercentage = likePercentage;
    }
    public Integer getReviews() {
        return reviews;
    }
    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    
}
