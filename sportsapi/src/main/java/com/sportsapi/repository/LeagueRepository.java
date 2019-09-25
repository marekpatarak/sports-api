package com.sportsapi.repository;

import com.sportsapi.entity.League;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueRepository extends CrudRepository<League, Integer> {

    List<League> findAllByCountry_CountryId(Integer countryId);

}
