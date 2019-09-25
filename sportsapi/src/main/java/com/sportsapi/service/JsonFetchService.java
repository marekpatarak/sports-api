package com.sportsapi.service;

import com.sportsapi.control.DataFetchType;
import com.sportsapi.entity.*;
import com.sportsapi.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JsonFetchService {

//    @Autowired
//    private Config config;

    @Value("${RAPID_API_URL_PREFIX}")
    private String rapidApiUrlPrefix;

    @Value("${RAPID_API_HOST}")
    private String rapidApiHost;

    @Value("${RAPID_API_KEY}")
    private String rapidApiKey;

    @Value("${SEASON}")
    private String season;

    @Value("${JSON_FETCH_SERVICE_ENABLED}")
    private String jsonFetchServiceEnabled;

    private final String RAPID_API_HOST_HEADER_KEY = "x-rapidapi-host";
    private final String RAPID_API_KEY_HEADER_KEY = "x-rapidapi-key";

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private TeamStatisticsRepository teamStatisticsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;

    private static Logger logger = Logger.getLogger(JsonFetchService.class.getCanonicalName());

    public void toggleJsonFetchServiceEnabled(){
        if (Boolean.valueOf(jsonFetchServiceEnabled)) {
            setJsonFetchServiceEnabled("false");
        } else {
            setJsonFetchServiceEnabled("true");
        }
    }

    private void setJsonFetchServiceEnabled(String fetchServiceEnabled) {
        this.jsonFetchServiceEnabled = fetchServiceEnabled;
    }

    public Boolean getJsonFetchServiceEnabled() {
        return Boolean.valueOf(jsonFetchServiceEnabled);
    }

    public void fetchData(DataFetchType dataFetchType, String param) {

        if(Boolean.valueOf(jsonFetchServiceEnabled)) {


            JSONObject jsonObject = fetchJSONObject(dataFetchType, param);

            if(dataFetchType != DataFetchType.TEAMSTATISTICS) {

                JSONArray jsonArray = jsonObject.getJSONObject("api").getJSONArray(dataFetchType.getFetchType());

                for (Object arrayObject : jsonArray) {
                    JSONObject entityObject = (JSONObject) arrayObject;

                    switch (dataFetchType) {

                        case COUNTRIES:
                            Country country = Country.getCountryFromJsonObject(entityObject);
                            countryRepository.save(country);
                            logger.log(Level.INFO, "Country " + country.getCountryName() + " saved");
                            break;

                        case LEAGUES:
                            League league = League.getLeagueFromJsonObject(entityObject);
                            Country leagueCountry = countryRepository.findCountryByCountryNameEquals(league.getCountry().getCountryName());

                            if (leagueCountry != null) {
                                league.setCountry(leagueCountry);
                            } else {
                                countryRepository.save(league.getCountry());
                            }

                            leagueRepository.save(league);
                            logger.log(Level.INFO, league.toString() + " saved");
                            break;

                        case TEAMS:
                            Team team = Team.getTeamFromJsonObject(entityObject);
                            League teamLeague = leagueRepository.findById(Integer.parseInt(param)).get();

                            if (teamLeague != null) {
                                team.setLeague(teamLeague);
                            } else {
                                leagueRepository.save(teamLeague);
                            }

                            teamsRepository.save(team);
                            logger.log(Level.INFO, team.toString() + " saved");

                            break;

                        case PLAYERS:
                            Team playerTeam = teamsRepository.findById(Integer.parseInt(param)).get();
                            Player player = Player.getPlayerFromJsonObject(entityObject);

                            if (playerTeam != null) {
                                player.setTeam(playerTeam);
                            }

                            playerRepository.save(player);
                            break;

                        case PLAYERSTATISTICS:
                            Player player1 = playerRepository.findById(Integer.parseInt(param)).get();
                            if (player1.getTeam().getLeague().getName().equals((String)entityObject.get("league"))) {
                                PlayerStatistics playerStatistics = PlayerStatistics.getPlayerStatisticsFromJsonObject(entityObject);
                                playerStatistics.setPlayer(player1);
                                playerStatisticsRepository.save(playerStatistics);
                            }
                            break;

                    }

                }
            } else {
                TeamStatistics teamStatistics = TeamStatistics.getTeamStatisticsFromJsonObject(jsonObject.getJSONObject("api").getJSONObject(dataFetchType.getFetchType()));
                Team team = teamsRepository.findById(Integer.parseInt(param)).get();
                teamStatistics.setTeam(team);
                teamStatisticsRepository.save(teamStatistics);
            }

            logger.log(Level.INFO,"Fetch of data *" + dataFetchType.getFetchType() + "* finished");
        } else {
            logger.log(Level.INFO,"Fetch service disabled");

        }
    }

    private String prepareUrl(DataFetchType dataFetchType, String param) {

        String url = rapidApiUrlPrefix + dataFetchType.getFetchType();

        switch(dataFetchType) {
            case COUNTRIES:
                break;
            case LEAGUES:
            case TEAMS:
                url += dataFetchType.getUrlSuffix() + param;
                break;
            case PLAYERS:
            case PLAYERSTATISTICS:
                url += dataFetchType.getUrlSuffix() + param + "/" + season;
                break;
            case TEAMSTATISTICS:
                Integer leagueId = teamsRepository.findById(Integer.parseInt(param)).get().getLeague().getLeagueId();
                url += dataFetchType.getUrlSuffix() + leagueId + "/" + param;
                break;

        }

        return url;
    }

    private JSONObject fetchJSONObject(DataFetchType dataFetchType, String param) {
        String url = prepareUrl(dataFetchType, param);
        StringBuilder strb = new StringBuilder();

        try {
            URL objUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty(RAPID_API_HOST_HEADER_KEY, rapidApiHost);
            con.setRequestProperty(RAPID_API_KEY_HEADER_KEY, rapidApiKey);

            if(con.getResponseCode() == 200) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    strb.append(s);
                }

                return new JSONObject(strb.toString());

            } else {
                throw new IOException("Response code not 200");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Could not fetch JSON Object, " + e.getMessage());
            return null;
        }
    }
}

