package com.threeraredyn.campbooka.service;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.model.BookingDTO;

@Service
public interface BookingService {
    
    public Boolean bookProperty(BookingDTO bookingDTO);
}
