package com.threeraredyn.campbooka.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.model.PropertyResponseDTO;

@Service
public interface PropertyService {
    
    public List<Property> findAll();
    public List<PropertyResponseDTO> findPropertyByPlaces(Places place);
    public boolean checkAlreadyExists(String propertyName, String placeName);
    public void save(Property property);
    public void saveAll(List<Property> propertyList);
    public Property findById(Long id);
}
