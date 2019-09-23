package com.sportsapi.sportsapi.repository;

import com.sportsapi.sportsapi.entity.PlayerStatistics;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatisticsRepository extends CrudRepository<PlayerStatistics, Integer> {
}
