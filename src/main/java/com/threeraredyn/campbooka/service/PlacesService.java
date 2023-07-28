package com.threeraredyn.campbooka.service;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Places;

@Service
public interface PlacesService {
    
    public Places findById(long id);

}
