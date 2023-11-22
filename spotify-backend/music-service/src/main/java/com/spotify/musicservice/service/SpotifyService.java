package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.SpotifyPlaylist;
import com.spotify.musicservice.dto.Track;
import com.spotify.musicservice.model.SpotifyAccessToken;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;

import java.util.List;

public interface SpotifyService {
    SpotifyAccessToken getSpotifyAccessToken();

    SpotifyPlaylist getBillBoard100Playlist();

    SpotifyPlaylist getTodayTopHits();

    Track getTrack(String trackId);

    Object search(String query);
}
