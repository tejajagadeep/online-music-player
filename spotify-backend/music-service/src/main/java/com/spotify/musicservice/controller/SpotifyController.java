package com.spotify.musicservice.controller;

import com.spotify.musicservice.exception.ResourceNotFoundException;
import com.spotify.musicservice.service.SpotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1.0/music")
public class SpotifyController {


    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/login")
    public ResponseEntity<Object> getSpotifyAccessToken() {
        // Obtain the access token
            return new ResponseEntity<>(spotifyService.getSpotifyAccessToken(), HttpStatus.OK);
    }

    @GetMapping("/bill-board-100-playlist")
    public ResponseEntity<Object> getBillBoard100Playlist() {
            return new ResponseEntity<>(spotifyService.getBillBoard100Playlist(), HttpStatus.OK);
    }

    @GetMapping("/today-top-hits-playlist")
    public ResponseEntity<Object> getTodayTopHits() {
            return new ResponseEntity<>(spotifyService.getTodayTopHits(), HttpStatus.OK);
    }

    @GetMapping("/get-track")
    public ResponseEntity<Object> getTrack(@RequestParam String trackId ) {
        return new ResponseEntity<>(spotifyService.getTrack(trackId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String query) {
            return new ResponseEntity<>(spotifyService.search(query), HttpStatus.OK);
    }
}
