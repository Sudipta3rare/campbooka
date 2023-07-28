package com.threeraredyn.campbooka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.service.PlacesService;

@RestController
public class PlacesController{

    @Autowired
    private PlacesService placesService;

    @GetMapping("/api/getPlacesById/{id}")
    @ResponseBody
    public Places getPlacesById(@PathVariable long id) {
        return placesService.findById(id);
    }
}