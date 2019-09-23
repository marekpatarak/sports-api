package com.sportsapi.repository;

import com.sportsapi.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamsRepository extends CrudRepository<Team, Integer>{

    public List<Team> findTeamsByLeague_LeagueId(Integer leagueId);
}
