package com.threeraredyn.campbooka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.model.PropertyByIdDTO;
import com.threeraredyn.campbooka.service.PlacesService;
import com.threeraredyn.campbooka.service.PropertyService;

@RestController
public class PropertyController {
    
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PlacesService placesService;

    @GetMapping("/api/getPropeties")
    @ResponseBody
    public List<Property> getProperties() {
        return propertyService.findAll();
    }

    @PostMapping("/api/postPropertyFromPlaceId")
    public List<Property> getPropertiesFromPlace(@RequestBody PropertyByIdDTO propertyByIdDTO ) {

        Optional<Places> placesOptional = placesService.findById(propertyByIdDTO.getPlaceId());

        if(placesOptional.isPresent())
            return propertyService.findPropertyByPlaces(placesOptional.get());
        else
            return null;
    }
}
