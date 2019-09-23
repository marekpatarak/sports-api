package com.sportsapi.sportsapi.repository;

import com.sportsapi.sportsapi.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamsRepository extends CrudRepository<Team, Integer>{

    public List<Team> findTeamsByLeague_LeagueId(Integer leagueId);
}
