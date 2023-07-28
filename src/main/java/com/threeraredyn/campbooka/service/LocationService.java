package com.threeraredyn.campbooka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Location;

@Service
public interface LocationService {
    
    public List<Location> searchLocation(String searchWord);
    public List<Location> getLocationByName(String placeName);
}
