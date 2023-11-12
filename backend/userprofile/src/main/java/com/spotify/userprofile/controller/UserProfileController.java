package com.spotify.userprofile.controller;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.entity.UserProfile;
import com.spotify.userprofile.exception.ResourceNotFoundException;
import com.spotify.userprofile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/userProfile")
public class UserProfileController {

    private final UserProfileService usersProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.usersProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        try {
            return new ResponseEntity<>(usersProfileService.getAllUsers(),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("No Data to show"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserProfileById(@PathVariable long id){
        try {
            return new ResponseEntity<>(usersProfileService.getUserProfileById(id),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("Id: "+id+"not found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserProfile userProfile){
        try {
            return new ResponseEntity<>(usersProfileService.saveUserProfile(userProfile),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("hello"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserProfile(@RequestBody UserProfileDto userProfileDto, @PathVariable long id){
        try {
            return new ResponseEntity<>(usersProfileService.updateUserProfile(userProfileDto, id),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResourceNotFoundException("hello"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
