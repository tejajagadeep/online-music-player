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
@RequestMapping("/api/v1/music")
public class SpotifyController {


    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/login")
    public ResponseEntity<Object> getSpotifyAccessToken() {
        // Obtain the access token
        try {
            return new ResponseEntity<>(spotifyService.getSpotifyAccessToken(), HttpStatus.OK);

        } catch (Exception e){
            throw new ResourceNotFoundException("");
        }

    }

    @GetMapping("/bill-board-100-playlist")
    public ResponseEntity<Object> getBillBoard100Playlist() {
        try {
            return new ResponseEntity<>(spotifyService.getBillBoard100Playlist(), HttpStatus.OK);
    } catch (Exception e){
        throw new ResourceNotFoundException("");
    }
    }

    @GetMapping("/today-top-hits-playlist")
    public ResponseEntity<Object> getTodayTopHits() {
        try {
            return new ResponseEntity<>(spotifyService.getTodayTopHits(), HttpStatus.OK);
        } catch (Exception e){
            throw new ResourceNotFoundException("");
            }
    }

    @GetMapping("/get-track")
    public ResponseEntity<Object> getTrack(@RequestParam String trackId ) {
        try {
        return new ResponseEntity<>(spotifyService.getTrack(trackId), HttpStatus.OK);
        } catch (Exception e){
            throw new ResourceNotFoundException("");
            }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String query) {
        try {
            return new ResponseEntity<>(spotifyService.search(query), HttpStatus.OK);
        } catch (Exception e){
            throw new ResourceNotFoundException("");
        }
    }
}
