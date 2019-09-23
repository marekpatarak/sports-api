package com.sportsapi.sportsapi.control;

public enum DataFetchType {

    COUNTRIES("countries",""),
    LEAGUES("leagues","/league/"),
    TEAMS("teams","/league/"),
    TEAMSTATISTICS("statistics","/"),
    PLAYERSTATISTICS("players","/player/"),
    PLAYERS("players","/squad/");


    private String fetchType;
    private String urlSuffix;

    DataFetchType(String fetchType, String urlSuffix) {
        this.fetchType = fetchType;
        this.urlSuffix = urlSuffix;

    }

    public String getFetchType() {
        return fetchType;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }
}
