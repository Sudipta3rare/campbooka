package com.threeraredyn.campbooka.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.projection.PropertyCardProjection;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    
    public List<Property> findAll();
    public List<PropertyCardProjection> findPropertyByPlace(Places place);
    public boolean existsByPropertyNameAndPlace(String propertyName, Places places);

}
