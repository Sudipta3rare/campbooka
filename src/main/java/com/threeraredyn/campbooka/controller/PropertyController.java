package com.threeraredyn.campbooka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.service.PropertyService;

@RestController
public class PropertyController {
    
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/api/getPropeties")
    @ResponseBody
    public List<Property> getProperties() {
        return propertyService.findAll();
    }
}
