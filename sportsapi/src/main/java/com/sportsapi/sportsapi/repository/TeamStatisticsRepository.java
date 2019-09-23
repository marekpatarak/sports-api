package com.sportsapi.sportsapi.repository;

import com.sportsapi.sportsapi.entity.TeamStatistics;
import org.springframework.data.repository.CrudRepository;

public interface TeamStatisticsRepository extends CrudRepository<TeamStatistics, Integer> {
}
