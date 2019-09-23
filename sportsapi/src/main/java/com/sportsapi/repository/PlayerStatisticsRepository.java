package com.sportsapi.repository;

import com.sportsapi.entity.PlayerStatistics;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatisticsRepository extends CrudRepository<PlayerStatistics, Integer> {
}
