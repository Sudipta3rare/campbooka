package com.threeraredyn.campbooka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.threeraredyn.campbooka.entity.Place;
import com.threeraredyn.campbooka.service.PlaceService;

@RestController
public class PlaceController {
    
    @Autowired
    private PlaceService placeService;

    @GetMapping("/api/searchPlace/{searchWord}")
    @ResponseBody
    public List<Place> searchPlace(@PathVariable String searchWord) {
        return placeService.searchPlace(searchWord);
    }


    @GetMapping("/api/getPlaceByName/{namePlace}")
    @ResponseBody
    public List<Place> searchPlaceByName(@PathVariable String namePlace){
        return placeService.getPlaceByName(namePlace);
    }
    
}