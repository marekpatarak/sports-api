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

    public static League getLeagueFromJsonObject(JSONObject jsonObject) {
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

}
