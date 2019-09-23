package com.sportsapi.repository;

import com.sportsapi.entity.TeamStatistics;
import org.springframework.data.repository.CrudRepository;

public interface TeamStatisticsRepository extends CrudRepository<TeamStatistics, Integer> {
}
