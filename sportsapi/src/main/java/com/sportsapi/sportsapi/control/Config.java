package com.sportsapi.sportsapi.control;


import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    public static String RAPID_API_URL_PREFIX = "x-rapidapi-url-prefix";
    public static String RAPID_API_HOST = "x-rapidapi-host";
    public static String RAPID_API_KEY = "x-rapidapi-key";
    public static String SEASON_2019 = "season-2019";
    public static String FETCH_SERVICE_ENABLED = "fetch-service-enabled";


    private static Map<String,String> properties = new HashMap<>();

    static {
        properties.put(RAPID_API_URL_PREFIX,"https://api-football-v1.p.rapidapi.com/v2/");
        properties.put(RAPID_API_HOST,"api-football-v1.p.rapidapi.com");
        properties.put(RAPID_API_KEY,"5f113824dcmsh433d1df9d5a7b7ep151f28jsne22a50e362d0");
        properties.put(SEASON_2019,"2019");
        properties.put(FETCH_SERVICE_ENABLED,"false");
//        properties.put(FETCH_SERVICE_ENABLED,"true");


    }

    public String getProperty(String propertyKey) {
        return properties.get(propertyKey);
    }

}
