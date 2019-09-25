package com.sportsapi.service;

import com.sportsapi.auth.registration.EmailExistsException;
import com.sportsapi.auth.registration.UserDto;
import com.sportsapi.entity.User;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
}
