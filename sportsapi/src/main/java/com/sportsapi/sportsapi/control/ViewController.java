package com.sportsapi.sportsapi.control;

import com.sportsapi.sportsapi.entity.Country;
import com.sportsapi.sportsapi.entity.League;
import com.sportsapi.sportsapi.entity.Player;
import com.sportsapi.sportsapi.entity.Team;
import com.sportsapi.sportsapi.repository.CountryRepository;
import com.sportsapi.sportsapi.repository.LeagueRepository;
import com.sportsapi.sportsapi.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path="/")
public class ViewController {

    @Autowired
    ViewService viewService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    LeagueRepository leagueRepository;


    @GetMapping(path="/")
    public String getLandingPage() {

        return "landingpage";
    }

    @GetMapping(path="/countries")
    public String getCountries(Model model) {

        List<Country> countries = viewService.getCountries();
        model.addAttribute("countries", countries);

        return "countries";
    }

    @GetMapping(path="/leagues")
    public String getLeagues(Model model) {

        List<League> leagues = viewService.getLeagues();
        model.addAttribute("leagues", leagues);
        return "leagues";
    }

    @GetMapping(path="/leagues/country/{countryid}")
    public String getLeaguesByCountry(@PathVariable("countryid") String countryId, Model model) {

        List<League> leagues = viewService.getLeaguesByCountry(countryId);
        model.addAttribute("leagues", leagues);
        return "leagues";
    }

    @GetMapping(path="/teams")
    public String getTeams(Model model) {

        List<Team> teams = viewService.getTeams();
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping(path="/teams/{leagueId}")
    public String getTeamsByLeague(@PathVariable("leagueId") String leagueId, Model model) {

        List<Team> teams = viewService.getTeamsByLeague(leagueId);
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping(path="/teams/country/{countryId}")
    public String getTeamsByCountry(@PathVariable("countryId") String countryId, Model model) {

        List<Team> teams = viewService.getTeamsByCountry(countryId);
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping(path="/teams/statistics/{leagueId}")
    public String getTeamStatisticsByLeague(@PathVariable("leagueId") String leagueId, Model model) {

        List<Team> teams = viewService.getTeamsByLeague(leagueId);
        Collections.sort(teams);

        model.addAttribute("teams", teams);

        return "teamStatistics";
    }

    @GetMapping(path="/players")
    public String getPlayers(Model model) {

        List<Player> players = viewService.getPlayers();
        Map<String,Country> countryMap = viewService.getCountryMap(players);
        model.addAttribute("players", players);
        model.addAttribute("countryMap", countryMap);

        return "players";
    }

    @GetMapping(path="/players/{teamId}")
    public String getPlayers(@PathVariable("teamId") String teamId, Model model) {

        List<Player> players = viewService.getPlayersByTeam(teamId);
        Map<String,Country> countryMap = viewService.getCountryMap(players);
        model.addAttribute("players", players);
        model.addAttribute("countryMap", countryMap);

        return "players";
    }
    @GetMapping(path="/players/country/{countryId}")
    public String getPlayersByCountry(@PathVariable("countryId") String countryId, Model model) {

        List<Player> players = viewService.getPlayersByCountry(countryId);
        Map<String,Country> countryMap = viewService.getCountryMap(players);

        model.addAttribute("players", players);
        model.addAttribute("countryMap", countryMap);

        return "players";
    }

    @GetMapping(path="/admin")
    public String getAdminPage() {

        return "admin";
    }





}
