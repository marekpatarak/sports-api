package com.sportsapi.sportsapi.repository;

import com.sportsapi.sportsapi.entity.League;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueRepository extends CrudRepository<League, Integer> {

    List<League> findAllByCountry_CountryId(Integer countryId);
}
