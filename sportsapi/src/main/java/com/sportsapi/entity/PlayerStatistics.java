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

}
