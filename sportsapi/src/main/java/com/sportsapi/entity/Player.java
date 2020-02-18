package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name="player")
@Getter
@Setter
public class Player {

    @Id
    private Integer playerId;
    private String firstName;
    private String lastName;
    private Integer number;
    private String position;
    private Integer age;
    private String birthDate;
    private String birthPlace;
    private String birthCountry;
    private String nationality;
    private String height;
    private String weight;
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @OneToOne(mappedBy = "player")
    private PlayerStatistics playerStatistics;

    public Player() {

    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + number +
                ", position='" + position + '\'' +
                ", age=" + age +
                ", birthDate='" + birthDate + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthCountry='" + birthCountry + '\'' +
                ", nationality='" + nationality + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", team=" + team +
                '}';
    }

}
