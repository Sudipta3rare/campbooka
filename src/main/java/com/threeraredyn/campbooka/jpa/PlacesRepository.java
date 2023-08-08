package com.threeraredyn.campbooka.jpa;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Location;
import com.threeraredyn.campbooka.entity.Places;

public interface PlacesRepository extends JpaRepository<Places, Long> {

    public Optional<Places> findById(int id);
    public Optional<Places> findByPlaceName(String placeName);
    public List<Places> findByLocation(Location location);

  
    
}
