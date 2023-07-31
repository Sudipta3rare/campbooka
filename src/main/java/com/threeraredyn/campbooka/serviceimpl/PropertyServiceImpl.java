package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.jpa.PropertyRepository;
import com.threeraredyn.campbooka.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> findPropertyByPlaces(Places place) {
        return propertyRepository.findPropertyByPlace(place);
    }

    
}