package com.threeraredyn.campbooka.service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.model.PropertyRequestDTO;
import com.threeraredyn.campbooka.model.PropertyResponseDTO;

@Service
public interface PropertyService {
    
    public List<Property> findAll();
    public List<PropertyResponseDTO> findPropertyByPlaces(Places place);
    public boolean checkAlreadyExists(String propertyName, String placeName);
    public void save(Property property);
    public void saveAll(List<Property> propertyList);
    public Property findById(Long id);
    public void addNewProperty(PropertyRequestDTO propertyRequestDTO);
    public void addNewProperties(List<PropertyRequestDTO> propertyRequestDTOs);
    public ByteArrayResource getPropertyImage(String filename) throws NoSuchFileException, IOException;
}
