package com.sportsapi.control;

import com.sportsapi.entity.Player;
import com.sportsapi.entity.Team;
import com.sportsapi.service.FetchService;
import com.sportsapi.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/jsonfetch")
public class FetchController {

    @Autowired
    FetchService fetchService;

    @Autowired
    ViewService viewService;

    @GetMapping(path="/countries")
    public String fetchCountries() {

        fetchService.fetchData(DataFetchType.COUNTRIES,"");

        return "success";
    }

    @GetMapping(path="/leagues/")
    @ResponseBody
    public String fetchLeague(@RequestParam("leagueId") String leagueId) {

        fetchService.fetchData(DataFetchType.LEAGUES, leagueId);

        return "success";
    }


    @GetMapping(path="/teams/")
    @ResponseBody
    public String fetchTeamsByLeague(@RequestParam("leagueId") String leagueId) {

        fetchService.fetchData(DataFetchType.TEAMS, leagueId);

        return "success";
    }

    @GetMapping(path="/players/")
    @ResponseBody
    public String fetchPlayersByTeam(@RequestParam("teamId") String teamId) {

        fetchService.fetchData(DataFetchType.PLAYERS, teamId);

        return "success";
    }

    @GetMapping(path="/teamstatistics/")
    @ResponseBody
    public String fetchTeamStatistics(@RequestParam Map<String,String> allParams) {

        if (allParams.get("teamId") != null) {

            fetchService.fetchData(DataFetchType.TEAMSTATISTICS, allParams.get("teamId"));

        } else if (allParams.get("leagueId") != null) {
            List<Team> teams = viewService.getTeamsByLeague(allParams.get("leagueId"));

            teams.stream().forEach(x-> {
                fetchService.fetchData(DataFetchType.TEAMSTATISTICS, String.valueOf(x.getTeamId()));

            });
        }


        return "success";
    }

    @GetMapping(path="/playerstatistics/")
    @ResponseBody
    public String fetchPlayerStatistics(@RequestParam Map<String,String> allParams) {

        if (allParams.get("playerId") != null) {
            fetchService.fetchData(DataFetchType.PLAYERSTATISTICS, allParams.get("playerId"));

        } else if (allParams.get("teamId") != null) {
            List<Player> players = viewService.getPlayersByTeam(allParams.get("teamId"));

            players.stream().forEach(x-> {
                fetchService.fetchData(DataFetchType.PLAYERSTATISTICS, String.valueOf(x.getPlayerId()));

            });
        }


        return "success";
    }

}
