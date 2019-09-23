package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity(name="team")
@Getter
@Setter
public class Team implements Comparable{

    @Id
    private Integer teamId;
    private String name;
    private String code;
    private String logo;
    @ManyToOne
    @JoinColumn(name="league_id")
    private League league;
    private Integer founded;
    private String venueName;
    private String venueAddress;
    private String venueCity;
    private Integer venueCapacity;
    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToOne(mappedBy = "team")
    private TeamStatistics teamStatistics;

    public Team() {
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", logo='" + logo + '\'' +
                ", founded=" + founded +
                ", venueName='" + venueName + '\'' +
                ", venueAddress='" + venueAddress + '\'' +
                ", venueCity='" + venueCity + '\'' +
                ", venueCapacity=" + venueCapacity +
                '}';
    }

    public static Team getTeamFromJsonObject(JSONObject jsonObject) {
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

    @Override
    public int compareTo(Object o) {
        Team otherTeam = (Team)o;
        if(teamStatistics.getPoints() < otherTeam.getTeamStatistics().getPoints()) {
            return 1;
        } else {
            return -1;
        }
    }
}
