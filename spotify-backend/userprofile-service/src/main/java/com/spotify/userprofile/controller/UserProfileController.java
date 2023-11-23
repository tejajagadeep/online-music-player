package com.spotify.userprofile.controller;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.kafka.DataPublisherServiceImpl;
import com.spotify.userprofile.model.UserDetails;
import com.spotify.userprofile.model.UserProfile;
import com.spotify.userprofile.service.AuthService;
import com.spotify.userprofile.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/userProfile")
@Slf4j
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final AuthService authService;

    private final DataPublisherServiceImpl producer;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, AuthService authService, DataPublisherServiceImpl producer) {
        this.userProfileService = userProfileService;
        this.authService = authService;
        this.producer = producer;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Object> getAllUsers(@RequestHeader("Authorization") String token){
        log.info(token+"from front end");
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------"+token+"inside method get all-----");
        if(info.containsValue("Admin")) {
            log.info(token+"inside method get all-----__---");
            return new ResponseEntity<>(userProfileService.getAllUsers(), HttpStatus.OK);
        }
        return new ResponseEntity<>("UnAuthorized", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/getUserById/{username}")
    public ResponseEntity<Object> getUserProfileById(@PathVariable String username){
            return new ResponseEntity<>(userProfileService.getUserProfileById(username),HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserProfile userProfile){
        UserDetails userDetails=new UserDetails();

        userDetails.setUsername(userProfile.getUsername());
        userDetails.setPassword(userProfile.getPassword());
        userDetails.setRole("User");
        log.info("------"+userDetails+"--------");
        producer.sendMessage(userDetails);
        return new ResponseEntity<>(userProfileService.saveUserProfile(userProfile),HttpStatus.OK);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Object> updateUserProfile(@RequestBody UserProfileDto userProfileDto, @PathVariable String username){
            return new ResponseEntity<>(userProfileService.updateUserProfile(userProfileDto, username),HttpStatus.OK);
    }


}
