package com.threeraredyn.campbooka.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

   
   
    public List<Location> findByLocationNameLike(String searchWord);
    public List<Location> findByLocationName(String placeName);
}
