package com.threeraredyn.campbooka.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Booking;
import com.threeraredyn.campbooka.entity.Property;
import com.threeraredyn.campbooka.entity.User;
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

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<BookingDTO, Booking> typeMapper = 
            modelMapper.createTypeMap(BookingDTO.class, Booking.class);

        Converter<Long, Property> idToProperty = ctx -> propertyService.findById(ctx.getSource());
        Converter<Long, User>     idToCamper   = ctx -> userService.findById(ctx.getSource());

        typeMapper.addMappings(mapper -> {
            mapper.using(idToProperty).map(BookingDTO::getPropertyId, Booking::setProperty);
            mapper.using(idToCamper).map(BookingDTO::getCamperId, Booking::setCamper);
        } );

        Booking booking = new Booking();

        booking.setCamper(userService.findById(bookingDTO.getCamperId()));
        booking.setProperty(propertyService.findById(bookingDTO.getPropertyId()));

        if(bookingRepository.existsByPropertyAndStartDateBetween(booking.getProperty(), bookingDTO.getStartDate(), bookingDTO.getEndDate()))
            return false;
        
        if(bookingRepository.existsByPropertyAndEndDateBetween(booking.getProperty(), bookingDTO.getStartDate(), bookingDTO.getEndDate()))
            return false;

        booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        return true;
    }

    @Override
    public List<BookingResponseDTO> getBookingHistory(String email) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        List<Booking> bookingList = 
            bookingRepository
                .findByIdIn(userService
                .findByUsername(email).getPropertySet()
                .stream().map(p -> p.getId()).collect(Collectors.toList()));
        
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
