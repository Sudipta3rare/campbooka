package com.threeraredyn.campbooka.model;

public class PlacesDTO {
    
    private Long id;
    private String placeName;
    private double acres;
    private String description;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getAcres() {
        return acres;
    }

    public void setAcres(double acres) {
        this.acres = acres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
}
