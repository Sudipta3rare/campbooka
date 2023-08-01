package com.threeraredyn.campbooka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.model.SearchPlacesDTO;
import com.threeraredyn.campbooka.service.LocationService;
import com.threeraredyn.campbooka.service.PlacesService;

@RestController
public class PlacesController{

    @Autowired
    private PlacesService placesService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/api/getPlacesById/{id}")
    @ResponseBody
    public Places getPlacesById(@PathVariable long id) {

        Optional<Places> placesOptional = placesService.findById(id);

        if(placesOptional.isPresent())
            return placesOptional.get();
        else
            return null; 
    }

    @GetMapping("/api/getPlacesByLocation/{locationId}")
    @ResponseBody
    public List<Places> getPlacesByLocation(@PathVariable Long locationId) {
        Optional<Location> locationOptional = locationService.findById(locationId);

        if(locationOptional.isPresent())
            return placesService.findByLocation(locationOptional.get());
        else
            return null;
    }

    @PostMapping("/api/getPlaceByLocation")
    @ResponseBody
    public List<Places> placesByLocationId(@RequestBody SearchPlacesDTO searchPlaceDTO){
       Optional<Location> locationOptional = locationService.findById(searchPlaceDTO.getPlaceId());

        if(locationOptional.isPresent())
            return placesService.findByLocation(locationOptional.get());
        else
            return null;
    } 
    
   
}