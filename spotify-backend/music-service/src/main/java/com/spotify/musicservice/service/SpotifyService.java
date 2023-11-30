package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.Album;
import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.dto.Track;
import com.spotify.musicservice.model.SpotifyAccessToken;

public interface SpotifyService {
    String getSpotifyAccessToken();

    SpotifyPlaylist getBillBoard100Playlist();

    SpotifyPlaylist getTodayTopHitsPlaylist();

    SpotifyPlaylist getDiscoverWeeklyPlaylist();

    SpotifyPlaylist getPlaylist(String playlistId);

    Track getTrack(String trackId);

    Album getAlbum(String albumId);

    Object searchTracks(String query);

    Object searchPlaylists(String query);

    Object searchAlbums(String query);

}
