package com.threeraredyn.campbooka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.model.BookingDTO;
import com.threeraredyn.campbooka.model.BookingResponseDTO;

@Service
public interface BookingService {
    
    public Boolean bookProperty(BookingDTO bookingDTO);
    public List<BookingResponseDTO> getBookingHistory(Long id);
}
