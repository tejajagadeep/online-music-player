package com.spotify.musicservice.controller;

import ch.qos.logback.core.model.Model;
import com.spotify.musicservice.exception.ResourceAlreadyExistsException;
import com.spotify.musicservice.exception.ResourceNotFoundException;
import com.spotify.musicservice.model.SpotifyAccessToken;
import com.spotify.musicservice.service.SpotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/music")
public class SpotifyController {


    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/access-token")
    public ResponseEntity<Object> getSpotifyAccessToken() {
        // Obtain the access token
            return new ResponseEntity<>(spotifyService.getSpotifyAccessToken(), HttpStatus.OK);

    }

    @GetMapping("/bill-board-100-playlist")
    public Object getBillBoard100Playlist() {
            return new ResponseEntity<>(spotifyService.getBillBoard100Playlist(), HttpStatus.OK);
    }

    @GetMapping("/today-top-hits-playlist")
    public Object getTodayTopHits() {
            return new ResponseEntity<>(spotifyService.getTodayTopHits(), HttpStatus.OK);
    }

}
