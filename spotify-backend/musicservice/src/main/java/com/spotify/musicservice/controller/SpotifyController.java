package com.spotify.musicservice.controller;

import com.spotify.musicservice.service.SpotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

@RestController
@Slf4j
public class SpotifyController {


    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }


    @GetMapping("/authorize")
    public String authorize() {
        // Redirect the user to Spotify authorization endpoint
        return spotifyService.authorize();

    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        return spotifyService.callback(code);
    }


    @GetMapping(value = "/home")
    public String home(@RequestParam String userId) {
        try {

            return userId;
        } catch (Exception e) {
            log.info("Exception while landing to home page: " + e);
        }

        return null;
    }


    @GetMapping(value = "/user-top-songs")
    public ResponseEntity<List<Track>> getUserTopSongs(@RequestParam String userId) {

        try {
            return new ResponseEntity<>(spotifyService.getUserTopSongs(userId), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Exception occurred while fetching top songs: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
