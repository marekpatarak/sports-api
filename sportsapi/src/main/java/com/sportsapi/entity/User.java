package com.sportsapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Boolean enabled;


}
