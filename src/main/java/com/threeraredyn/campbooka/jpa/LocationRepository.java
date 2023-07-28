package com.threeraredyn.campbooka.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

   
   
    public List<Location> findTop3ByLocationNameOrCountryName(String searchWord, String searchWord2);
    public List<Location> findByLocationName(String placeName);
}
