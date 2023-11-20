package com.shalem.authenticationservice.service;

import com.shalem.authenticationservice.entity.User;
import com.shalem.authenticationservice.payload.JwtResponse;
import com.shalem.authenticationservice.payload.LoginDto;
import com.shalem.authenticationservice.payload.RegisterDto;

import java.util.List;

public interface AuthService {
    User addUser(RegisterDto registerDto);

    JwtResponse login(LoginDto loginDto);

    List<User> fetchAllUsers();
}
