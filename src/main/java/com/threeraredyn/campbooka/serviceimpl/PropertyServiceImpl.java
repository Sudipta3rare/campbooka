package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.jpa.PlacesRepository;
import com.threeraredyn.campbooka.jpa.PropertyRepository;
import com.threeraredyn.campbooka.model.PropertyRequestDTO;
import com.threeraredyn.campbooka.model.PropertyResponseDTO;
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
    public List<PropertyResponseDTO> findPropertyByPlaces(Places place) {

        return propertyRepository
                    .findPropertyByPlace(place)
                    .stream().map(property -> {
            PropertyResponseDTO propertyResponseDTO = new PropertyResponseDTO();
            propertyResponseDTO.setId(property.getId());
            propertyResponseDTO.setDescription(property.getDescrip());
            propertyResponseDTO.setAccomodationType(property.getAccomodationType());
            propertyResponseDTO.setArea(property.getArea());
            propertyResponseDTO.setPropertyName(property.getPropertyName());
            propertyResponseDTO.setLikePercentage(property.getLikePercentage());
            propertyResponseDTO.setPrice(property.getPrice());
            propertyResponseDTO.setReviews(property.getReviews());
            return propertyResponseDTO;

        }).collect(Collectors.toList());
    }

    @Override
    public boolean checkAlreadyExists(String propertyName, String placeName) {
        Optional<Places> placesOptional = placesRepository.findByPlaceName(placeName);
        if(!placesOptional.isPresent())
            return false;

        return propertyRepository.existsByPropertyNameAndPlace(propertyName, placesOptional.get());
    }

    @Override
    public void save(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public void saveAll(List<Property> propertyList) {
        propertyRepository.saveAll(propertyList);
    }

    @Override
    public Property findById(Long id) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if(!propertyOptional.isPresent())
            return null;
        
        return propertyOptional.get();
    }

    @Override
    public void addNewProperty(PropertyRequestDTO propertyRequestDTO) {

        Property property = new Property();
        property.setPropertyName(propertyRequestDTO.getPropertyName());
        property.setAccomodationType(propertyRequestDTO.getPropertyType());
        property.setDescrip(propertyRequestDTO.getDescription());
        property.setArea(propertyRequestDTO.getArea());
        property.setPrice(propertyRequestDTO.getPrice());

        Optional<Places> placesOptional = placesRepository.findByPlaceName(propertyRequestDTO.getPlaceName());
        
        if(placesOptional.isPresent())
            property.setPlace(placesOptional.get());
        
        propertyRepository.save(property);
    }

    @Override
    public void addNewProperties(List<PropertyRequestDTO> propertyRequestDTOList) {

        List<Property> propertyList = propertyRequestDTOList.stream()
            .filter(prop -> !checkAlreadyExists(prop.getPropertyName(), prop.getPlaceName()))
            .map( prop -> {
                Property property = new Property();
                property.setPropertyName(prop.getPropertyName());
                property.setAccomodationType(prop.getPropertyType());
                property.setDescrip(prop.getDescription());
                property.setArea(prop.getArea());
                property.setPrice(prop.getPrice());

                Optional<Places> placesOptional = placesRepository.findByPlaceName(prop.getPlaceName());
        
                if(placesOptional.isPresent())
                    property.setPlace(placesOptional.get());
                return property;
            }).collect(Collectors.toList());
        
        propertyRepository.saveAll(propertyList);
    }

    
}