package com.threeraredyn.campbooka.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Booking;
import com.threeraredyn.campbooka.enumeration.BookingStatus;
import com.threeraredyn.campbooka.jpa.BookingRepository;
import com.threeraredyn.campbooka.model.BookingDTO;
import com.threeraredyn.campbooka.service.BookingService;
import com.threeraredyn.campbooka.service.PropertyService;
import com.threeraredyn.campbooka.service.UserService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired 
    private PropertyService propertyService;

    @Override
    public Boolean bookProperty(BookingDTO bookingDTO) {

        Booking booking = new Booking();

        booking.setCamper(userService.findById(bookingDTO.getCamperId()));
        booking.setProperty(propertyService.findById(bookingDTO.getPropertyId()));

        if(bookingRepository.existsByPropertyAndStartDateBetween(booking.getProperty(), bookingDTO.getStartDate(), bookingDTO.getEndDate()))
            return false;
        
        if(bookingRepository.existsByPropertyAndEndDateBetween(booking.getProperty(), bookingDTO.getStartDate(), bookingDTO.getEndDate()))
            return false;


        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setNoOfAdults(bookingDTO.getNoOfAdults());
        booking.setNoOfChildren(bookingDTO.getNoOfChildren());
        booking.setNoOfVehicles(bookingDTO.getNoOfVehicles());

        bookingRepository.save(booking);
        return true;
    }
}
