package com.sportsapi;

import com.sportsapi.entity.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonObjectMapper {

    public Country getCountryFromJsonObject(JSONObject jsonObject) {
        if(jsonObject != null) {
            Country country = new Country();

            country.setCountryName(!jsonObject.get("country").equals(null) ? (String)jsonObject.get("country") : null);
            country.setCode(!jsonObject.get("code").equals(null) ? (String)jsonObject.get("code") : null);
            country.setFlag(!jsonObject.get("flag").equals(null) ? (String)jsonObject.get("flag") : null);

            return country;
        }
        return null;
    }

    public League getLeagueFromJsonObject(JSONObject jsonObject) {
        if(jsonObject != null) {
            Country country = new Country();
            country.setCountryName(!jsonObject.get("country").equals(null) ? (String)jsonObject.get("country") : null);
            country.setCode(!jsonObject.get("country_code").equals(null) ? (String)jsonObject.get("country_code") : null);

            League league = new League();
            league.setLeagueId(!jsonObject.get("league_id").equals(null) ? (Integer)jsonObject.get("league_id") : null);
            league.setName(jsonObject.get("name").equals(null) ? (String)jsonObject.get("name") : null);
            league.setSeason(!jsonObject.get("season").equals(null) ? (Integer)jsonObject.get("season") : null);
            league.setSeasonStart(!jsonObject.get("season_start").equals(null) ? (String)jsonObject.get("season_start") : null);
            league.setSeasonEnd(!jsonObject.get("season_end").equals(null) ? (String)jsonObject.get("season_end") : null);
            league.setLogo(!jsonObject.get("logo").equals(null) ? (String)jsonObject.get("logo") : null);
            league.setStandings((Integer) jsonObject.get("standings") == 1 ? true : false);
            league.setIsCurrent((Integer) jsonObject.get("is_current") == 1 ? true : false);
            league.setCountry(country);

            return league;
        }
        return null;
    }

    public Team getTeamFromJsonObject(JSONObject jsonObject) {
        if(jsonObject != null) {
            Team team = new Team();
            team.setTeamId(!jsonObject.get("team_id").equals(null) ? (Integer)jsonObject.get("team_id") : null);
            team.setName(!jsonObject.get("name").equals(null) ? (String)jsonObject.get("name") : null);
            team.setCode(!jsonObject.get("code").equals(null) ? (String)jsonObject.get("code") : null);
            team.setLogo(!jsonObject.get("logo").equals(null) ? (String)jsonObject.get("logo") : null);
            team.setFounded(!jsonObject.get("founded").equals(null) ? (Integer)jsonObject.get("founded") : null);
            team.setVenueName(!jsonObject.get("venue_name").equals(null) ? (String)jsonObject.get("venue_name") : null);
            team.setVenueAddress (!jsonObject.get("venue_address").equals(null) ? (String)jsonObject.get("venue_address") : null);
            team.setVenueCity(!jsonObject.get("venue_city").equals(null) ? (String)jsonObject.get("venue_city") : null);
            team.setVenueCapacity(!jsonObject.get("venue_capacity").equals(null) ? (Integer)jsonObject.get("venue_capacity") : null);

            return team;
        }
        return null;
    }

    public Player getPlayerFromJsonObject(JSONObject jsonObject) {
        if(jsonObject != null) {
            Player player = new Player();
            player.setPlayerId(!jsonObject.get("player_id").equals(null) ? (Integer)jsonObject.get("player_id") : null);
            player.setFirstName(!jsonObject.get("firstname").equals(null) ? (String)jsonObject.get("firstname") : null);
            player.setLastName(!jsonObject.get("lastname").equals(null) ? (String)jsonObject.get("lastname") : null);
            player.setNumber(!jsonObject.get("number").equals(null) ? (Integer)jsonObject.get("number") : null);
            player.setPosition(!jsonObject.get("position").equals(null) ? (String)jsonObject.get("position") : null);
            player.setAge(!jsonObject.get("age").equals(null) ? (Integer)jsonObject.get("age") : null);
            player.setBirthDate(!jsonObject.get("birth_date").equals(null) ? (String)jsonObject.get("birth_date") : null);
            player.setBirthPlace(!jsonObject.get("birth_place").equals(null) ? (String)jsonObject.get("birth_place") : null);
            player.setBirthCountry(!jsonObject.get("birth_country").equals(null) ? (String)jsonObject.get("birth_country") : null);
            player.setNationality(!jsonObject.get("nationality").equals(null) ? (String)jsonObject.get("nationality") : null);
            player.setHeight(!jsonObject.get("height").equals(null) ? (String)jsonObject.get("height") : null);
            player.setWeight(!jsonObject.get("weight").equals(null) ? (String)jsonObject.get("weight") : null);

            return player;
        }
        return null;
    }

    public PlayerStatistics getPlayerStatisticsFromJsonObject(JSONObject jsonObject) {
        if(jsonObject != null) {
            PlayerStatistics playerStatistics = new PlayerStatistics();
            playerStatistics.setInjured(jsonObject.getString("injured").toLowerCase().equals("true") ? true : false);
            playerStatistics.setRating(Double.parseDouble(jsonObject.getString("rating")));
            playerStatistics.setCaptain(jsonObject.getInt("captain") == 1 ? true : false);
            playerStatistics.setShotsTotal(jsonObject.getJSONObject("shots").getInt("total"));
            playerStatistics.setShotsOn(jsonObject.getJSONObject("shots").getInt("on"));
            playerStatistics.setGoalsTotal(jsonObject.getJSONObject("goals").getInt("total"));
            playerStatistics.setGoalsConceded(jsonObject.getJSONObject("goals").getInt("conceded"));
            playerStatistics.setGoalsAssists(jsonObject.getJSONObject("goals").getInt("assists"));
            playerStatistics.setPassesTotal(jsonObject.getJSONObject("passes").getInt("total"));
            playerStatistics.setPassesKey(jsonObject.getJSONObject("passes").getInt("key"));
            playerStatistics.setPassesAccuracy(jsonObject.getJSONObject("passes").getInt("accuracy"));
            playerStatistics.setTacklesTotal(jsonObject.getJSONObject("tackles").getInt("total"));
            playerStatistics.setTacklesBlocks(jsonObject.getJSONObject("tackles").getInt("blocks"));
            playerStatistics.setTacklesInterceptions(jsonObject.getJSONObject("tackles").getInt("interceptions"));
            playerStatistics.setDuelsTotal(jsonObject.getJSONObject("duels").getInt("total"));
            playerStatistics.setDuelsWon(jsonObject.getJSONObject("duels").getInt("won"));
            playerStatistics.setDribbleAttempts(jsonObject.getJSONObject("dribbles").getInt("attempts"));
            playerStatistics.setDribbleSuccess(jsonObject.getJSONObject("dribbles").getInt("success"));
            playerStatistics.setFoulsDrawn(jsonObject.getJSONObject("fouls").getInt("drawn"));
            playerStatistics.setFoulsCommited(jsonObject.getJSONObject("fouls").getInt("commited"));
            playerStatistics.setCardsYellow(jsonObject.getJSONObject("cards").getInt("yellow"));
            playerStatistics.setCardsYellowRed(jsonObject.getJSONObject("cards").getInt("yellowred"));
            playerStatistics.setCardsRed(jsonObject.getJSONObject("cards").getInt("red"));
            playerStatistics.setPenaltyWon(jsonObject.getJSONObject("penalty").getInt("won"));
            playerStatistics.setPenaltyCommited(jsonObject.getJSONObject("penalty").getInt("commited"));
            playerStatistics.setPenaltySuccess(jsonObject.getJSONObject("penalty").getInt("success"));
            playerStatistics.setPenaltyMissed(jsonObject.getJSONObject("penalty").getInt("missed"));
            playerStatistics.setPenaltySaved(jsonObject.getJSONObject("penalty").getInt("saved"));
            playerStatistics.setGamesAppearences(jsonObject.getJSONObject("games").getInt("appearences"));
            playerStatistics.setGamesMinutesPlayed(jsonObject.getJSONObject("games").getInt("minutes_played"));
            playerStatistics.setGamesLineUps(jsonObject.getJSONObject("games").getInt("lineups"));
            playerStatistics.setSubstitutesIn(jsonObject.getJSONObject("substitutes").getInt("in"));
            playerStatistics.setSubstitutesOut(jsonObject.getJSONObject("substitutes").getInt("out"));
            playerStatistics.setSubstitutesBench(jsonObject.getJSONObject("substitutes").getInt("bench"));

            return playerStatistics;
        }
        return null;
    }

    public TeamStatistics getTeamStatisticsFromJsonObject(JSONObject jsonObject) {
        JSONObject matchs = jsonObject.getJSONObject("matchs");
        JSONObject goals = jsonObject.getJSONObject("goals");
        TeamStatistics teamStatistics = new TeamStatistics();
        if (matchs != null) {
            Integer winsHome = (Integer) matchs.getJSONObject("wins").get("home");
            teamStatistics.setWinsHome(winsHome);
            Integer winsAway = (Integer) matchs.getJSONObject("wins").get("away");
            teamStatistics.setWinsAway(winsAway);
            Integer drawsHome = (Integer) matchs.getJSONObject("draws").get("home");
            teamStatistics.setDrawsHome(drawsHome);
            Integer drawsAway = (Integer) matchs.getJSONObject("draws").get("away");
            teamStatistics.setDrawsAway(drawsAway);
            Integer losesHome = (Integer) matchs.getJSONObject("loses").get("home");
            teamStatistics.setLosesHome(losesHome);
            Integer losesAway = (Integer) matchs.getJSONObject("loses").get("away");
            teamStatistics.setLosesAway(losesAway);
        }

        if (goals != null) {
            Integer goalsForHome = (Integer) goals.getJSONObject("goalsFor").get("home");
            teamStatistics.setGoalsForHome(goalsForHome);
            Integer goalsForAway = (Integer) goals.getJSONObject("goalsFor").get("away");
            teamStatistics.setGoalsForAway(goalsForAway);
            Integer goalsAgainstHome = (Integer) goals.getJSONObject("goalsAgainst").get("home");
            teamStatistics.setGoalsAgainstHome(goalsAgainstHome);
            Integer goalsAgainstAway = (Integer) goals.getJSONObject("goalsAgainst").get("away");
            teamStatistics.setGoalsAgainstAway(goalsAgainstAway);
        }

        return teamStatistics;
    }
}
