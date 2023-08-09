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

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.model.PlacesDTO;
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

    @PostMapping("/api/admin/addNewPlace")
    @ResponseBody
    public ResponseEntity<?> addNewPlace(@RequestBody PlacesDTO placesDTO) {

        if(placesService.checkAlreadyExists(placesDTO.getPlaceName()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Places places = new Places();

        places.setAcres(placesDTO.getAcres());
        places.setPlaceName(placesDTO.getPlaceName());
        places.setDescrip(placesDTO.getDescription());

        placesService.save(places);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/admin/addNewPlaces")
    @ResponseBody
    public ResponseEntity<?> addNewPlaces(@RequestBody List<PlacesDTO> placesDTOList) {

        List<Places> eligiblePlaces = placesDTOList.stream()
                                            .filter(p -> !placesService.checkAlreadyExists(p.getPlaceName()))
                                            .map(p -> {
                                                Places places = new Places();
                                                places.setPlaceName(p.getPlaceName());
                                                places.setAcres(p.getAcres());
                                                places.setDescrip(p.getDescription());
                                                return places;
                                            }).collect(Collectors.toList());
        if(eligiblePlaces.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        placesService.saveAll(eligiblePlaces);
        return ResponseEntity.ok().build();
    }
}