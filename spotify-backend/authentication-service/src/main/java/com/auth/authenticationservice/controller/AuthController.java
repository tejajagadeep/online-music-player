package com.shalem.authenticationservice.controller;

import com.shalem.authenticationservice.entity.User;
import com.shalem.authenticationservice.payload.JwtResponse;
import com.shalem.authenticationservice.payload.LoginDto;
import com.shalem.authenticationservice.payload.RegisterDto;
import com.shalem.authenticationservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        log.info("in controller register method");
        return new ResponseEntity<>(authService.addUser(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.login(loginDto),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(authService.fetchAllUsers(),HttpStatus.OK);
    }



}
