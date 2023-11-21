package com.spotify.userprofile.controller;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.exception.ResourceNotFoundException;
import com.spotify.userprofile.model.UserProfile;
import com.spotify.userprofile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/userProfile")
public class UserProfileController {

//    Logger logger = (Logger) LoggerFactory.getLogger(UserProfileController.class);
    private final UserProfileService usersProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.usersProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
            return new ResponseEntity<>(usersProfileService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserProfileById(@PathVariable long id){
            return new ResponseEntity<>(usersProfileService.getUserProfileById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserProfile userProfile){
            return new ResponseEntity<>(usersProfileService.saveUserProfile(userProfile),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserProfile(@RequestBody UserProfileDto userProfileDto, @PathVariable long id){
            return new ResponseEntity<>(usersProfileService.updateUserProfile(userProfileDto, id),HttpStatus.OK);
    }

}
