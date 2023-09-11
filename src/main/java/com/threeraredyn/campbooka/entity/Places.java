package com.threeraredyn.campbooka.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="places")
public class Places
 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Media media;
   
    private double acres;
    private String descrip;
    private int noOfSites;

    @ManyToOne
    private Location location;

    private String placeName;

    @ManyToMany
    @JoinTable(name = "places_activity",
        joinColumns = @JoinColumn(name = "places_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"))
    private Set<Activity> activitySet;

    @ManyToMany
    @JoinTable(name = "places_feature",
        joinColumns = @JoinColumn(name = "places_id"),
        inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "id"))
    private Set<Feature> featureSet;
    
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Media getMedia() {
        return media;
    }
    public void setMedia(Media media) {
        this.media = media;
    }
    public double getAcres() {
        return acres;
    }
    public void setAcres(double acres) {
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
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Set<Activity> getActivitySet() {
        return activitySet;
    }
    public void setActivitySet(Set<Activity> activitySet) {
        this.activitySet = activitySet;
    }
    public Set<Feature> getFeatureSet() {
        return featureSet;
    }
    public void setFeatureSet(Set<Feature> featureSet) {
        this.featureSet = featureSet;
    }
}
