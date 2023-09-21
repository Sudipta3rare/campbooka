package com.threeraredyn.campbooka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.service.LocationService;

@RestController
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    @GetMapping("/api/public/searchLocation/{searchWord}")
    public ResponseEntity<?> searchLocation(@PathVariable String searchWord) {
        List<Location> locationList = locationService.searchLocation(searchWord);
        if(locationList == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(locationList);
    }

    
    @GetMapping("/api/getLocationByName/{namePlace}")
    public ResponseEntity<?> searchPlaceByName(@PathVariable String namePlace) {
        List<Location> locationList =  locationService.getLocationByName(namePlace);
        return ResponseEntity.ok().body(locationList);
    }
    
}