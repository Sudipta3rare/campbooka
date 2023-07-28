package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Place;
import com.threeraredyn.campbooka.jpa.PlaceRepository;
import com.threeraredyn.campbooka.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public List<Place> searchPlace(String searchWord) {
        return placeRepository.findTop3ByPlaceNameOrCountryName(searchWord, searchWord);
    }

    @Override
    public List<Place> getPlaceByName(String placeName) {
        
        // Optional<Place> placeOptional = placeRepository.findByPlaceName(placeName);
        return placeRepository.findByPlaceName(placeName);

        // if(placeOptional.isPresent())
        //     return placeOptional.get();
        // else
        //     return null;
    }

    
}
