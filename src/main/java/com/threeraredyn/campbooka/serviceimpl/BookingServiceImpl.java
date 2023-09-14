package com.threeraredyn.campbooka.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Booking;
import com.threeraredyn.campbooka.enumeration.BookingStatus;
import com.threeraredyn.campbooka.jpa.BookingRepository;
import com.threeraredyn.campbooka.model.BookingDTO;
import com.threeraredyn.campbooka.model.BookingResponseDTO;
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

    @Override
    public List<BookingResponseDTO> getBookingHistory(Long id) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        List<Booking> bookingList = bookingRepository.findAllById(
                                        userService.findById(id).getPropertySet()
                                            .stream().map(p -> p.getId())
                                            .collect(Collectors.toList()));
        
        return bookingList.stream().map(b -> {
            BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
            bookingResponseDTO.setBookingDate(simpleDateFormat.format(b.getStartDate()));
            bookingResponseDTO.setCamperName(b.getCamper().getFirstName() + " " + b.getCamper().getLastName());
            bookingResponseDTO.setImageUrl(null);
            bookingResponseDTO.setPayableAmount(b.getProperty().getPrice());
            bookingResponseDTO.setPropertyName(b.getProperty().getPropertyName());
            return bookingResponseDTO;
        }).collect(Collectors.toList());                        
    }

    
}
