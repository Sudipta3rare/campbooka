package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.jpa.PlacesRepository;
import com.threeraredyn.campbooka.service.PlacesService;

@Service
public class PlacesServiceImpl implements PlacesService{

        @Autowired
        private PlacesRepository placeRepository;

        
        @Override
        public Optional<Places> findById(long id) {
            return placeRepository.findById(id);

        }

        @Override
        public List<Places> findByLocation(Location location) {
            return placeRepository.findByLocation(location);
        }

        @Override
        public Optional<Places> findByPlaceName(String placeName) {
            return placeRepository.findByPlaceName(placeName);
        }

    }