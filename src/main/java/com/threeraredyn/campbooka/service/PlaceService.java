package com.threeraredyn.campbooka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Place;

@Service
public interface PlaceService {
    
    public List<Place> searchPlace(String searchWord);
    public List<Place> getPlaceByName(String placeName);
}
