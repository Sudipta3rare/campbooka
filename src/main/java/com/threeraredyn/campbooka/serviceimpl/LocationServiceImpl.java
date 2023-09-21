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
        // % symbol is used as wildcard character.
        return locationRepository.findByLocationNameLike(searchWord + "%");
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
