package com.threeraredyn.campbooka.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.jpa.PlacesRepository;
import com.threeraredyn.campbooka.jpa.PropertyRepository;
import com.threeraredyn.campbooka.jpa.UserRepository;
import com.threeraredyn.campbooka.model.PropertyRequestDTO;
import com.threeraredyn.campbooka.model.PropertyResponseDTO;
import com.threeraredyn.campbooka.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PlacesRepository placesRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${campbooka.project.path}")
    private String projectPath;

    @Value("${campbooka.project.folder.property}")
    private String propertyFolderName;

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public List<PropertyResponseDTO> findPropertyByPlaces(Places place) {
        ModelMapper modelMapper = new ModelMapper();

        return propertyRepository.findPropertyByPlace(place)
               .stream()
               .map(property -> {
                    PropertyResponseDTO propertyResponseDTO = 
                        modelMapper.map(property, PropertyResponseDTO.class);
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

        ModelMapper modelMapper = new ModelMapper();
        modelMapper
            .createTypeMap(PropertyRequestDTO.class, Property.class)
            .addMappings(mapper -> mapper.map(src -> src.getPropertyType(), Property::setAccomodationType));
        
        Property property = modelMapper.map(propertyRequestDTO, Property.class);

        Optional<Places> placesOptional = placesRepository.findByPlaceName(propertyRequestDTO.getPlaceName());
        if(placesOptional.isPresent())
            property.setPlace(placesOptional.get());

        Optional<User> hostOptional = userRepository.findByEmail(propertyRequestDTO.getHostEmail());

        if(hostOptional.isPresent()) {
            
            if(hostOptional.get().getPropertySet() == null)
                hostOptional.get().setPropertySet(Set.of(property));
            else
                hostOptional.get().getPropertySet().add(property);

            property.setHostSet(Set.of(hostOptional.get()));
        }
        
        propertyRepository.save(property);
    }

    @Override
    public void addNewProperties(List<PropertyRequestDTO> propertyRequestDTOList) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper
            .createTypeMap(PropertyRequestDTO.class, Property.class)
            .addMappings(mapper -> mapper.map(src -> src.getPropertyType(), Property::setAccomodationType));

        List<Property> propertyList = propertyRequestDTOList.stream()
            .filter(prop -> !checkAlreadyExists(prop.getPropertyName(), prop.getPlaceName()))
            .map( prop -> {
                Property property = modelMapper.map(prop, Property.class);
                Optional<Places> placesOptional = placesRepository.findByPlaceName(prop.getPlaceName());
                if(placesOptional.isPresent())
                    property.setPlace(placesOptional.get());
                return property;
            }).collect(Collectors.toList());
        
        propertyRepository.saveAll(propertyList);
    }

    @Override
    public ByteArrayResource getPropertyImage(String filename) throws NoSuchFileException, IOException {
        ByteArrayResource inputStream = new
        ByteArrayResource(
            Files.readAllBytes(
                Paths.get(
                    projectPath 
                    + String.format("/assets/%s/", propertyFolderName) 
                    + filename + ".png"
                )
            )
        );
        return inputStream;
    }  
}