package com.example.sporifyservicedemo.service;


import com.example.sporifyservicedemo.model.SpotifyPlaylist;
import com.example.sporifyservicedemo.model.SpotifyTracks;
import com.example.sporifyservicedemo.model.Track;

public interface SpotifyService {
    SpotifyPlaylist getBillBoard100Playlist();

    SpotifyPlaylist saveSpotifyPlaylist(SpotifyPlaylist spotifyPlaylist);

    SpotifyPlaylist getDiscoverWeeklyPlaylist();

    SpotifyPlaylist getTodayTopHitsPlaylist();

    SpotifyTracks saveTracks(SpotifyTracks spotifyTracks);

    SpotifyTracks search(String query);

    Track saveTrack(Track track);

    Track getTrack(String trackId);
}
