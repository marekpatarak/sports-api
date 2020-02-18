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
