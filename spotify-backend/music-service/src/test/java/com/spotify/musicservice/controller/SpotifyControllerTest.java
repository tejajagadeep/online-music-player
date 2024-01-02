package com.spotify.musicservice.controller;

import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.dto.SpotifyTracksSearch;
import com.spotify.musicservice.service.SpotifyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SpotifyControllerTest {

    @Mock
    private SpotifyService spotifyService;

    @InjectMocks
    private SpotifyController spotifyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSpotifyAccessToken() {
        when(spotifyService.getSpotifyAccessToken()).thenReturn("testAccessToken");

        ResponseEntity<Object> response = spotifyController.getSpotifyAccessToken();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testAccessToken", response.getBody());
    }

    @Test
    void testGetBillBoard100Playlist() {
        when(spotifyService.getBillBoard100Playlist()).thenReturn(new SpotifyPlaylist());

        ResponseEntity<Object> response = spotifyController.getBillBoard100Playlist();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(SpotifyPlaylist.class, response.getBody().getClass());
    }

    @Test
    void testGetTodayTopHitsPlaylist() {
        when(spotifyService.getTodayTopHitsPlaylist()).thenReturn(new SpotifyPlaylist());

        ResponseEntity<Object> response = spotifyController.getTodayTopHitsPlaylist();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(SpotifyPlaylist.class, response.getBody().getClass());
    }

    @Test
    void testPersonalPlaylists() {
        when(spotifyService.personalPlaylists()).thenReturn(new SpotifyPlaylist());

        ResponseEntity<Object> response = spotifyController.personalPlaylists();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(SpotifyPlaylist.class, response.getBody().getClass());
    }

    @Test
    void testGetPlaylist() {
        when(spotifyService.getPlaylist("playlistId")).thenReturn(new SpotifyPlaylist());

        ResponseEntity<Object> response = spotifyController.getPlaylist("playlistId");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(SpotifyPlaylist.class, response.getBody().getClass());
    }



    @Test
    void testSearchTracks() {
        when(spotifyService.searchTracks("query")).thenReturn(new SpotifyTracksSearch());

        ResponseEntity<Object> response = spotifyController.searchTracks("query");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(SpotifyTracksSearch.class, response.getBody().getClass());
    }

    @Test
    void testSearchPlaylists() {
        when(spotifyService.searchPlaylists("query")).thenReturn(new SpotifyTracksSearch());

        ResponseEntity<Object> response = spotifyController.searchPlaylists("query");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(SpotifyTracksSearch.class, response.getBody().getClass());
    }
}
