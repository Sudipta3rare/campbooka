package com.threeraredyn.campbooka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.service.LocationService;

@RestController
public class LoactionController {
    
    @Autowired
    private LocationService locationService;

    @GetMapping("/api/searchLocation/{searchWord}")
    @ResponseBody
    public List<Location> searchLocation(@PathVariable String searchWord) {
        return locationService.searchLocation(searchWord);
    }

    
    @GetMapping("/api/getLocationByName/{namePlace}")
    @ResponseBody
    public List<Location> searchPlaceByName(@PathVariable String namePlace){
        return locationService.getLocationByName(namePlace);
    }
    
}