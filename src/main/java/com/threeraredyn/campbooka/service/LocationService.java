package com.threeraredyn.campbooka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Location;

@Service
public interface LocationService {
    
    public List<Location> searchLocation(String searchWord);
    public Optional<Location> findById(Long id);
}
