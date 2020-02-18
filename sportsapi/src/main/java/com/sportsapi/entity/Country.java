package com.sportsapi.entity;

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

}

