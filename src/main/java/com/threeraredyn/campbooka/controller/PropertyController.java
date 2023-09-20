package com.threeraredyn.campbooka.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.model.PropertyByIdDTO;
import com.threeraredyn.campbooka.model.PropertyRequestDTO;
import com.threeraredyn.campbooka.model.PropertyResponseDTO;
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

    @GetMapping("/api/public/postPropertyFromPlace/{id}")
    public ResponseEntity<?> getPropertiesFromPlace(@PathVariable Long id) {

        Optional<Places> placesOptional = placesService.findById(id);

        if(placesOptional.isPresent()) {
            List<PropertyResponseDTO> propertyList = propertyService.findPropertyByPlaces(placesOptional.get());
            return ResponseEntity.ok().body(propertyList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/host/addNewProperty")
    public ResponseEntity<?> addNewProperty(@RequestBody PropertyRequestDTO propertyDTO) {

        if(propertyService.checkAlreadyExists(propertyDTO.getPropertyName(), propertyDTO.getPlaceName()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Property property = new Property();
        property.setPropertyName(propertyDTO.getPropertyName());
        property.setAccomodationType(propertyDTO.getPropertyType());
        property.setDescrip(propertyDTO.getDescription());
        property.setArea(propertyDTO.getArea());
        property.setPrice(propertyDTO.getPrice());

        Optional<Places> placesOptional = placesService.findByPlaceName(propertyDTO.getPlaceName());
        
        if(placesOptional.isPresent())
            property.setPlace(placesOptional.get());
        
        propertyService.save(property);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/host/addNewProperties")
    public ResponseEntity<?> addNewProperties(@RequestBody List<PropertyRequestDTO> propertyDTOList) {

        List<Property> propertyList = propertyDTOList.stream()
            .filter(prop -> !propertyService.checkAlreadyExists(prop.getPropertyName(), prop.getPlaceName()))
            .map( prop -> {
                Property property = new Property();
                property.setPropertyName(prop.getPropertyName());
                property.setAccomodationType(prop.getPropertyType());
                property.setDescrip(prop.getDescription());
                property.setArea(prop.getArea());
                property.setPrice(prop.getPrice());

                Optional<Places> placesOptional = placesService.findByPlaceName(prop.getPlaceName());
        
                if(placesOptional.isPresent())
                    property.setPlace(placesOptional.get());
                return property;
            }).collect(Collectors.toList());

        if(propertyList.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        propertyService.saveAll(propertyList);
        return ResponseEntity.ok().build();
    }
}
