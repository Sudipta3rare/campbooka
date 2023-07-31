package com.threeraredyn.campbooka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;
import com.threeraredyn.campbooka.entity.Property;

@Service
public interface PropertyService {
    
    public List<Property> findAll();
    public List<Property> findPropertyByPlaces(Places place);
}
