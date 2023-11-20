package com.spotify.musicservice.service;

import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

public interface SpotifyService {
    String authorize();
    String callback(String code);
    List<Track> getUserTopSongs(String userId);
}
