package com.spotify.musicservice.controller;

import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.dto.SpotifyTracksSearch;
import com.spotify.musicservice.service.SpotifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.specification.Track;


@RestController
@Slf4j
@RequestMapping("/api/v1.0/music")
public class SpotifyController {


    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Operation(summary = "Store access Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access Token Saved",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Please try again after few some time",
                    content = @Content) })
    @GetMapping("/login")
    public ResponseEntity<Object> getSpotifyAccessToken() {
        // Obtain the access token
        log.trace("Inside SpotifyController getSpotifyAccessToken");
        return new ResponseEntity<>(spotifyService.getSpotifyAccessToken(), HttpStatus.OK);
    }


    @Operation(summary = "Get Bill Board 100 Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Bill Board 100 Playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpotifyPlaylist.class)) }),
            @ApiResponse(responseCode = "404", description = "Playlist not found",
                    content = @Content) })
    @GetMapping("/billBoard100Playlist")
    public ResponseEntity<Object> getBillBoard100Playlist() {
        log.trace("Inside SpotifyController getBillBoard100Playlist");
        return new ResponseEntity<>(spotifyService.getBillBoard100Playlist(), HttpStatus.OK);
    }

    @Operation(summary = "Get Top Hits Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Top Hits  Playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpotifyPlaylist.class)) }),
            @ApiResponse(responseCode = "404", description = "Playlist not found",
                    content = @Content) })
    @GetMapping("/todayTopHitsPlaylist")
    public ResponseEntity<Object> getTodayTopHitsPlaylist() {
        log.trace("Inside SpotifyController getTodayTopHitsPlaylist");
        return new ResponseEntity<>(spotifyService.getTodayTopHitsPlaylist(), HttpStatus.OK);
    }
    @Operation(summary = "Get Discover Weekly Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Discover Weekly Playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpotifyPlaylist.class)) }),
            @ApiResponse(responseCode = "404", description = "Playlist not found",
                    content = @Content) })
    @GetMapping("/discoverWeeklyPlaylist")
    public ResponseEntity<Object> getDiscoverWeeklyPlaylist() {
        log.trace("Inside SpotifyController getDiscoverWeeklyPlaylist");
        return new ResponseEntity<>(spotifyService.getDiscoverWeeklyPlaylist(), HttpStatus.OK);
    }
    @Operation(summary = "Get Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpotifyPlaylist.class)) }),
            @ApiResponse(responseCode = "404", description = "Playlist not found",
                    content = @Content) })
    @GetMapping("/playlist")
    public ResponseEntity<Object> getPlaylist(@RequestParam String playlistId) {
        log.trace("Inside SpotifyController getDiscoverWeeklyPlaylist");
        return new ResponseEntity<>(spotifyService.getPlaylist(playlistId), HttpStatus.OK);
    }

    @Operation(summary = "Get a track by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the track",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Track.class)) }),
            @ApiResponse(responseCode = "404", description = "Track not found",
                    content = @Content) })
    @GetMapping("/track")
    public ResponseEntity<Object> getTrack(@RequestParam String trackId ) {
        log.trace("Inside SpotifyController getTrack");
        return new ResponseEntity<>(spotifyService.getTrack(trackId), HttpStatus.OK);
    }

    @Operation(summary = "Search tracks by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tracks by name",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpotifyTracksSearch.class)) }),
            @ApiResponse(responseCode = "404", description = "Track Not Found",
                    content = @Content) })
    @GetMapping("/searchTracks")
    public ResponseEntity<Object> searchTracks(@RequestParam String query) {
        log.trace("Inside SpotifyController search");
        return new ResponseEntity<>(spotifyService.searchTracks(query), HttpStatus.OK);
    }
    @Operation(summary = "Search Playlists by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Playlists by name",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpotifyTracksSearch.class)) }),
            @ApiResponse(responseCode = "404", description = "Playlists Not Found",
                    content = @Content) })
    @GetMapping("/searchPlaylists")
    public ResponseEntity<Object> searchPlaylists(@RequestParam String query) {
        log.trace("Inside SpotifyController search");
        return new ResponseEntity<>(spotifyService.searchPlaylists(query), HttpStatus.OK);
    }

}
