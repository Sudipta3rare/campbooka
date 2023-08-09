package com.threeraredyn.campbooka.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    
    public List<Property> findAll();
    public List<Property> findPropertyByPlace(Places place);
    public boolean existsByPropertyNameAndPlaces(String propertyName, Places places);

}
