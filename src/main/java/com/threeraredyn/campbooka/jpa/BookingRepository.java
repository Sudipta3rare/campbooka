package com.threeraredyn.campbooka.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Booking;
import com.threeraredyn.campbooka.entity.Property;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    public boolean existsByPropertyAndStartDateBetween(Property property, Date x, Date y);
    public boolean existsByPropertyAndEndDateBetween(Property property, Date x, Date y);
}
