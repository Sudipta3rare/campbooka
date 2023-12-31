package com.threeraredyn.campbooka.projection;

public interface PropertyCardProjection {
    Long getId();
    String getPropertyName();
    Integer getNoOfSites();
    String getAccomodationType();
    Double getArea();
    String getDescription();
    Double getPrice();
    Integer getLikePercentage();
    Integer getReviews();
}
