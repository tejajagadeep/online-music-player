package com.example.sporifyservicedemo.controller;

import com.example.sporifyservicedemo.model.SpotifyPlaylist;
import com.example.sporifyservicedemo.model.SpotifyTracks;
import com.example.sporifyservicedemo.model.Track;
import com.example.sporifyservicedemo.service.SpotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/music")
@CrossOrigin("*")
public class SpotifyController {


    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }


    @GetMapping("/billBoard100Playlist")
    public ResponseEntity<Object> getBillBoard100Playlist() {
        log.trace("Inside SpotifyController getBillBoard100Playlist");
        return new ResponseEntity<>(spotifyService.getBillBoard100Playlist(), HttpStatus.OK);
    }

    @GetMapping("/discoverWeeklyPlaylist")
    public ResponseEntity<Object> getDiscoverWeeklyPlaylist() {
        log.trace("Inside SpotifyController getBillBoard100Playlist");
        return new ResponseEntity<>(spotifyService.getDiscoverWeeklyPlaylist(), HttpStatus.OK);
    }

    @GetMapping("/todayTopHitsPlaylist")
    public ResponseEntity<Object> getTodayTopHitsPlaylist() {
        log.trace("Inside SpotifyController getTodayTopHitsPlaylist");
        return new ResponseEntity<>(spotifyService.getTodayTopHitsPlaylist(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String query) {
        log.trace("Inside SpotifyController search");
        return new ResponseEntity<>(spotifyService.search(query), HttpStatus.OK);
    }

    @GetMapping("/track")
    public ResponseEntity<Object> getTrack(@RequestParam String trackId ) {
        log.trace("Inside SpotifyController getTrack");
        return new ResponseEntity<>(spotifyService.getTrack(trackId), HttpStatus.OK);
    }

    @PostMapping("/tracks")
    public ResponseEntity<Object> saveTracks(@RequestBody SpotifyTracks spotifyTracks){
        log.trace("Inside SpotifyController getBillBoard100Playlist");
        return new ResponseEntity<>(spotifyService.saveTracks(spotifyTracks), HttpStatus.OK);
    }

    @PostMapping("/track")
    public ResponseEntity<Object> saveTrack(@RequestBody Track track){
        log.trace("Inside SpotifyController getBillBoard100Playlist");
        return new ResponseEntity<>(spotifyService.saveTrack(track), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveSpotifyPlaylist(@RequestBody SpotifyPlaylist spotifyPlaylist){
        log.trace("Inside SpotifyController getBillBoard100Playlist");
        return new ResponseEntity<>(spotifyService.saveSpotifyPlaylist(spotifyPlaylist), HttpStatus.OK);
    }

}
