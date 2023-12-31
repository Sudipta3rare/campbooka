package com.threeraredyn.campbooka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.model.BookingDTO;
import com.threeraredyn.campbooka.model.HostRequestDTO;
import com.threeraredyn.campbooka.service.BookingService;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/api/user/book")
    public ResponseEntity<?> bookProperty(@RequestBody BookingDTO bookingDTO) {

        boolean result = bookingService.bookProperty(bookingDTO);
        if(result)
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/api/host/getBookingHistory")
    public ResponseEntity<?> getBookingHistory(@RequestBody HostRequestDTO hostRequestDTO) {
        return ResponseEntity.ok().body(bookingService.getBookingHistory(hostRequestDTO.getEmail()));
    }
    
}
