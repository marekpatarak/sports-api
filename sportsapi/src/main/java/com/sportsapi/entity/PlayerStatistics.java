package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name="playerstatistics")
@Getter
@Setter
public class PlayerStatistics {

    @Id
    @GeneratedValue
    private Integer id;
    private Boolean injured;
    private Double rating;
    private Boolean captain;
    private Integer shotsTotal;
    private Integer shotsOn;
    private Integer goalsTotal;
    private Integer goalsConceded;
    private Integer goalsAssists;
    private Integer passesTotal;
    private Integer passesKey;
    private Integer passesAccuracy;
    private Integer tacklesTotal;
    private Integer tacklesBlocks;
    private Integer tacklesInterceptions;
    private Integer duelsTotal;
    private Integer duelsWon;
    private Integer dribbleAttempts;
    private Integer dribbleSuccess;
    private Integer foulsDrawn;
    private Integer foulsCommited;
    private Integer cardsYellow;
    private Integer cardsYellowRed;
    private Integer cardsRed;
    private Integer penaltyWon;
    private Integer penaltyCommited;
    private Integer penaltySuccess;
    private Integer penaltyMissed;
    private Integer penaltySaved;
    private Integer gamesAppearences;
    private Integer gamesMinutesPlayed;
    private Integer gamesLineUps;
    private Integer substitutesIn;
    private Integer substitutesOut;
    private Integer substitutesBench;

    @OneToOne
    @JoinColumn(name="playerId")
    private Player player;

    public PlayerStatistics() {

    }


    public static PlayerStatistics getPlayerStatisticsFromJsonObject(JSONObject jsonObject) {
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
}
