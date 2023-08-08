package com.threeraredyn.campbooka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.entity.Places;

@Service
public interface PlacesService {
    
    public Optional<Places> findById(long id);
    public List<Places> findByLocation(Location location);
    public Optional<Places> findByPlaceName(String placeName);
}
