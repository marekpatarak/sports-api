package com.sportsapi.sportsapi.entity;

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

    public static TeamStatistics getTeamStatisticsFromJsonObject(JSONObject jsonObject) {
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
