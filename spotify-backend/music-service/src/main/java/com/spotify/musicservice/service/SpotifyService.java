package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.dto.Track;

public interface SpotifyService {
    String getSpotifyAccessToken();

    SpotifyPlaylist getBillBoard100Playlist();

    SpotifyPlaylist getTodayTopHitsPlaylist();

    SpotifyPlaylist personalPlaylists();

    SpotifyPlaylist getPlaylist(String playlistId);

    Track getTrack(String trackId);

    Object searchTracks(String query);

    Object searchPlaylists(String query);

}
