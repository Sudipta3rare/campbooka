package com.threeraredyn.campbooka.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.jpa.PlacesRepository;
import com.threeraredyn.campbooka.service.PlacesService;

@Service
public class PlacesServiceImpl implements PlacesService{

        @Autowired
        private PlacesRepository placeRepository;

        
        @Override
        public Places findById(long id) {
            Optional<Places> placeOptional = placeRepository.findById(id);

            if(placeOptional.isPresent())
                return placeOptional.get();
            else
                return null;

        }
}