package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity(name="league")
@Getter
@Setter
public class League {

    // Premier league id = 524
    // Primera division id = 775
    // Serie A id = 891
    // Bundesliga id = 754
    // Ligue 1 id = 525

    @Id
    private Integer leagueId;
    private String name;
    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;
    private Integer season;
    private String seasonStart;
    private String seasonEnd;
    private String logo;
    private Boolean standings;
    private Boolean isCurrent;
    @OneToMany(mappedBy = "league")
    private List<Team> teams;

    private String xmlFeedUrl;

    public League() {

    }

    @Override
    public String toString() {
        return "League{" +
                "leagueId=" + leagueId +
                ", name='" + name + '\'' +
                ", season=" + season +
                ", seasonStart='" + seasonStart + '\'' +
                ", seasonEnd='" + seasonEnd + '\'' +
                ", logo='" + logo + '\'' +
                ", standings=" + standings +
                ", isCurrent=" + isCurrent +
                ", xmlFeedUrl='" + xmlFeedUrl + '\'' +
                '}';
    }

}
