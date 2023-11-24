package com.spotify.userprofile.controller;

import com.spotify.userprofile.dto.UserProfileDto;
import com.spotify.userprofile.exception.UnAuthorizedException;
import com.spotify.userprofile.kafka.DataPublisherServiceImpl;
import com.spotify.userprofile.dto.UserDetails;
import com.spotify.userprofile.model.UserProfile;
import com.spotify.userprofile.service.AuthService;
import com.spotify.userprofile.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Admin Access Get Users Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Details Found",content =@Content) })
    @GetMapping("/getAllUser")
    public ResponseEntity<Object> getAllUsers(@RequestHeader("Authorization") String token){
        log.info(token+" : token from authentication to access get all users");
        Map<String,String> info= authService.validateToken(token);
        log.info("inside getAllUsers----info: "+info);
        if(!info.containsValue("Admin")) {
            log.info(token+"inside method get all-----__---");
            throw new UnAuthorizedException("Un Authorized Please check the details.");

        }
        return new ResponseEntity<>(userProfileService.getAllUsers(), HttpStatus.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Users Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Details Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileDto.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content) })
    @GetMapping("/getUserById/{username}")
    public ResponseEntity<Object> getUserProfileById(@RequestHeader("Authorization") String token,@PathVariable String username){

        log.info(token+" : token from authentication to access getUserProfileById");
        Map<String,String> info= authService.validateToken(token);
        log.info("inside getUserProfileById----info: "+info);
        if(!info.containsKey(username)) {
            log.error(token + "inside method getUserProfileById -----__---");
            throw new UnAuthorizedException("Un Authorized Please check user the details.");

        }
        return new ResponseEntity<>(userProfileService.getUserProfileById(username), HttpStatus.OK);
    }

    @Operation(summary = "Save User's Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Details Saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileDto.class)) }),
            @ApiResponse(responseCode = "409", description = "User Details already Exists",
                    content = @Content) })
    @PostMapping("/addUser")
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserProfile userProfile){
        UserDetails userDetails=new UserDetails();
        UserProfileDto userProfileDto=userProfileService.saveUserProfile(userProfile);
        userDetails.setUsername(userProfile.getUsername());
        userDetails.setPassword(userProfile.getPassword());
        userDetails.setRole("User");
        log.info("------"+userDetails+"--------");
        producer.sendMessage(userDetails);
        return new ResponseEntity<>(userProfileDto,HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Users Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Details Updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileDto.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "User Details already Exists",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content) })
    @PutMapping("/update/{username}")
    public ResponseEntity<Object> updateUserProfile(@RequestHeader("Authorization") String token,@RequestBody UserProfileDto userProfileDto, @PathVariable String username){

        log.info(token+" : token from authentication to access updateUserProfile");
        Map<String,String> info= authService.validateToken(token);
        log.info("info: "+info+"inside updateUserProfile----");
        if(!info.containsKey(username)) {
            log.error(token + "inside method updateUserProfile-----__---");
            throw new UnAuthorizedException("Un Authorized Please check user the details to update.");
        }
        return new ResponseEntity<>(userProfileService.updateUserProfile(userProfileDto, username),HttpStatus.OK);

    }


}
