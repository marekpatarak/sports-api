package com.sportsapi.control;

import com.sportsapi.entity.Player;
import com.sportsapi.entity.Team;
import com.sportsapi.service.JsonFetchService;
import com.sportsapi.service.ViewService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/jsonfetch")
@Log
public class JsonFetchController {

    @Autowired
    JsonFetchService jsonFetchService;

    @Autowired
    ViewService viewService;

    @GetMapping(path="/countries")
    public String fetchCountries() {

        if (jsonFetchService.getJsonFetchServiceEnabled()) {

            jsonFetchService.fetchJsonData(DataFetchType.COUNTRIES,"");
            return "success";

        } else {

            log.info("JSON fetch service disabled");
            return "service disabled";

        }

    }

    @GetMapping(path="/leagues/")
    @ResponseBody
    public String fetchLeague(@RequestParam("leagueId") String leagueId) {

        if(jsonFetchService.getJsonFetchServiceEnabled()) {

            jsonFetchService.fetchJsonData(DataFetchType.LEAGUES, leagueId);
            return "success";

        } else {

            log.info("JSON fetch service disabled");
            return "servicedisabled";
        }

    }


    @GetMapping(path="/teams/")
    @ResponseBody
    public String fetchTeamsByLeague(@RequestParam("leagueId") String leagueId) {

        if (jsonFetchService.getJsonFetchServiceEnabled()) {

            jsonFetchService.fetchJsonData(DataFetchType.TEAMS, leagueId);
            return "success";

        } else {

            log.info("JSON fetch service disabled");
            return "servicedisabled";
        }

    }

    @GetMapping(path="/players/")
    @ResponseBody
    public String fetchPlayersByTeam(@RequestParam("teamId") String teamId) {

        if (jsonFetchService.getJsonFetchServiceEnabled()) {

            jsonFetchService.fetchJsonData(DataFetchType.PLAYERS, teamId);
            return "success";
        } else {

            log.info("JSON fetch service disabled");
            return "servicedisabled";
        }

    }

    @GetMapping(path="/teamstatistics/")
    @ResponseBody
    public String fetchTeamStatistics(@RequestParam Map<String,String> allParams) {

        if (jsonFetchService.getJsonFetchServiceEnabled()) {

            if (allParams.get("teamId") != null) {

                jsonFetchService.fetchJsonData(DataFetchType.TEAMSTATISTICS, allParams.get("teamId"));

            } else if (allParams.get("leagueId") != null) {
                List<Team> teams = viewService.getTeamsByLeague(allParams.get("leagueId"));

                teams.stream().forEach(x -> {
                    jsonFetchService.fetchJsonData(DataFetchType.TEAMSTATISTICS, String.valueOf(x.getTeamId()));

                });
            }
            return "success";

        } else {

            log.info("JSON fetch service disabled");
            return "servicedisabled";
        }
    }

    @GetMapping(path="/playerstatistics/")
    @ResponseBody
    public String fetchPlayerStatistics(@RequestParam Map<String,String> allParams) {

        if (jsonFetchService.getJsonFetchServiceEnabled()) {
            if (allParams.get("playerId") != null) {
                jsonFetchService.fetchJsonData(DataFetchType.PLAYERSTATISTICS, allParams.get("playerId"));

            } else if (allParams.get("teamId") != null) {
                List<Player> players = viewService.getPlayersByTeam(allParams.get("teamId"));

                players.stream().forEach(x -> {
                    jsonFetchService.fetchJsonData(DataFetchType.PLAYERSTATISTICS, String.valueOf(x.getPlayerId()));
                });
            }

            return "success";
        } else {

            log.info("JSON fetch service disabled");
            return "servicedisabled";
        }
    }

    @GetMapping(path="/servicetoggle")
    public String toggleJsonFetchService() {

        jsonFetchService.toggleJsonFetchServiceEnabled();

        return "togglesuccess";
    }

}
