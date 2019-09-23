package com.sportsapi.sportsapi.repository;

import com.sportsapi.sportsapi.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    Country findCountryByCountryNameEquals(String countryName);
}
