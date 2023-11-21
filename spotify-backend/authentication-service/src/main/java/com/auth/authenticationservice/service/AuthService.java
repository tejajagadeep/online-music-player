package com.auth.authenticationservice.service;

import com.auth.authenticationservice.entity.User;
import com.auth.authenticationservice.payload.JwtResponse;
import com.auth.authenticationservice.payload.LoginDto;
import com.auth.authenticationservice.payload.RegisterDto;

import java.util.List;

public interface AuthService {
    User addUser(RegisterDto registerDto);

    JwtResponse login(LoginDto loginDto);

    List<User> fetchAllUsers();
}
