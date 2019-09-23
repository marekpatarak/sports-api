package com.sportsapi.repository;

import com.sportsapi.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    Country findCountryByCountryNameEquals(String countryName);
}
