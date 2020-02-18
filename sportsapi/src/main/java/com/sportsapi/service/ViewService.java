package com.sportsapi.service;

import com.sportsapi.entity.Country;
import com.sportsapi.entity.League;
import com.sportsapi.entity.Player;
import com.sportsapi.entity.Team;
import com.sportsapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final CountryRepository countryRepository;

    private final LeagueRepository leagueRepository;

    private final TeamsRepository teamsRepository;

    private final TeamStatisticsRepository teamStatisticsRepository;

    private final PlayerRepository playersRepository;

    private static Logger logger = Logger.getLogger(ViewService.class.getCanonicalName());

    public List<Country> getCountries() {

        return (List<Country>)countryRepository.findAll();
    }

    public List<League> getLeagues() {

        return (List<League>)leagueRepository.findAll();
    }

    public List<League> getLeaguesByCountry(String countryId) {

        return leagueRepository.findAllByCountry_CountryId(Integer.parseInt(countryId));
    }

    public List<Team> getTeams() {

        return (List<Team>)teamsRepository.findAll();
    }


    public List<Player> getPlayers() {

        return (List<Player>)playersRepository.findAll();
    }

    public Map<String,Country> getCountryMap(List<Player> players) {
        Map<String, Country> countryMap = new HashMap<>();

        for(Player player : players) {
            if(countryMap.get(player.getNationality()) == null) {
                Country countryNationality = countryRepository.findCountryByCountryNameEquals(player.getNationality());
                if (countryNationality != null) {
                    countryMap.put(countryNationality.getCountryName(), countryNationality);
                }
            }

            if(countryMap.get(player.getBirthCountry()) == null) {
                Country countryBirth = countryRepository.findCountryByCountryNameEquals(player.getBirthCountry());
                if (countryBirth != null) {
                    countryMap.put(countryBirth.getCountryName(), countryBirth);
                }
            }

        }

        return countryMap;

    }

    public List<Team> getTeamsByLeague(String leagueId) {
        return teamsRepository.findTeamsByLeague_LeagueId(Integer.parseInt(leagueId));
    }

    public List<Team> getTeamsByCountry(String countryId) {
        List<League> leaguesInCountry = leagueRepository.findAllByCountry_CountryId(Integer.parseInt(countryId));
        List<Team> teamsInCountry = new ArrayList<>();
        leaguesInCountry.stream().forEach(x -> teamsInCountry.addAll(teamsRepository.findTeamsByLeague_LeagueId(x.getLeagueId())));
        return teamsInCountry;
    }

    public List<Player> getPlayersByTeam(String teamId) {
        return playersRepository.findPlayersByTeam_TeamId(Integer.parseInt(teamId));
    }

    public List<Player> getPlayersByCountry(String countryId) {
        Country country = countryRepository.findById(Integer.parseInt(countryId)).get();
        return playersRepository.findPlayersByNationalityEquals(country.getCountryName());
    }
}

