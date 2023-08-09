package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.jpa.PlacesRepository;
import com.threeraredyn.campbooka.jpa.PropertyRepository;
import com.threeraredyn.campbooka.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PlacesRepository placesRepository; 

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> findPropertyByPlaces(Places place) {
        return propertyRepository.findPropertyByPlace(place);
    }

    @Override
    public boolean checkAlreadyExists(String propertyName, String placeName) {
        Optional<Places> placesOptional = placesRepository.findByPlaceName(placeName);
        if(!placesOptional.isPresent())
            return false;

        return propertyRepository.existsByPropertyNameAndPlaces(propertyName, placesOptional.get());
    }

    @Override
    public void save(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public void saveAll(List<Property> propertyList) {
        propertyRepository.saveAll(propertyList);
    }
}