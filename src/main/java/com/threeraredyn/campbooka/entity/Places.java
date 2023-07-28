package com.threeraredyn.campbooka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="places")
public class Places
 {
    @Id
    private int id;

    private Media media;
   
    private int acres;
    private String descrip;

    private int noOfSites;

    private Location location;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   public Media getMedia() {
        return media;
    }
    public void setMedia(Media media) {
        this.media = media;
    }
    public int getAcres() {
        return acres;
    }
    public void setAcres(int acres) {
        this.acres = acres;
    }
    public String getDescrip() {
        return descrip;
    }
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    public int getNoOfSites() {
        return noOfSites;
    }
    public void setNoOfSites(int noOfSites) {
        this.noOfSites = noOfSites;
    }
    public Location getLocationId() {
        return location;
    }
    public void setLocationId(Location locationId) {
        this.location = locationId;
    }


}
