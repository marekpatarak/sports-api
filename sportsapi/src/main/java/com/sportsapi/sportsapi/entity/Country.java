package com.sportsapi.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity(name="country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue
    private Integer countryId;

    private String countryName;
    private String code;
    private String flag;

    @OneToMany(mappedBy = "country")
    private List<League> leagues;

    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", code='" + code + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public static Country getCountryFromJsonObject(JSONObject jsonObject) {
        if(jsonObject != null) {
            Country country = new Country();

            country.setCountryName(!jsonObject.get("country").equals(null) ? (String)jsonObject.get("country") : null);
            country.setCode(!jsonObject.get("code").equals(null) ? (String)jsonObject.get("code") : null);
            country.setFlag(!jsonObject.get("flag").equals(null) ? (String)jsonObject.get("flag") : null);

            return country;
        }
        return null;
    }
}

