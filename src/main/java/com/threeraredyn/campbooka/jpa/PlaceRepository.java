package com.threeraredyn.campbooka.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threeraredyn.campbooka.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    public List<Place> findTop3ByPlaceNameOrCountryName(String placeWord, String countryWord);
    public List<Place> findByPlaceName(String placeName);
}
