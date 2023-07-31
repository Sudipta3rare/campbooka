package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.jpa.LocationRepository;
import com.threeraredyn.campbooka.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> searchLocation(String searchWord) {
        return locationRepository.findTop3ByLocationNameOrCountryName(searchWord, searchWord);
    }

    @Override
    public List<Location> getLocationByName(String placeName) {
        
        // Optional<Place> placeOptional = placeRepository.findByPlaceName(placeName);
        return locationRepository.findByLocationName(placeName);

        // if(placeOptional.isPresent())
        //     return placeOptional.get();
        // else
        //     return null;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    
}
