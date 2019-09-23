package com.sportsapi.repository;

import com.sportsapi.entity.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer>{

    public List<Player> findPlayersByTeam_TeamId(Integer teamId);

    List<Player> findPlayersByNationalityEquals(String countryName);

}
