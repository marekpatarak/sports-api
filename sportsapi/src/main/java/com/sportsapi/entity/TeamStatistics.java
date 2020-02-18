package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name="teamstatistics")
@Getter
@Setter
public class TeamStatistics {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer winsHome;
    private Integer winsAway;
    private Integer drawsHome;
    private Integer drawsAway;
    private Integer losesHome;
    private Integer losesAway;
    private Integer goalsForHome;
    private Integer goalsForAway;
    private Integer goalsAgainstHome;
    private Integer goalsAgainstAway;
    @OneToOne
    @JoinColumn(name="team_id")
    private Team team;


    public Integer getPoints() {
        Integer points = 0;
        if(getWinsAway() != null && getWinsHome() != null && getDrawsAway() != null && getDrawsHome() != null) {
            points += (winsAway + winsHome) * 3;
            points += (drawsAway + drawsHome) * 1;
        }
        return points;
    }

    @Override
    public String toString() {
        return "TeamStatistics{" +
                "id=" + id +
                ", winsHome=" + winsHome +
                ", winsAway=" + winsAway +
                ", drawsHome=" + drawsHome +
                ", drawsAway=" + drawsAway +
                ", losesHome=" + losesHome +
                ", losesAway=" + losesAway +
                ", goalsForHome=" + goalsForHome +
                ", goalsForAway=" + goalsForAway +
                ", goalsAgainstHome=" + goalsAgainstHome +
                ", goalsAgainstAway=" + goalsAgainstAway +
                ", team=" + team.getTeamId() +
                '}';
    }

}
