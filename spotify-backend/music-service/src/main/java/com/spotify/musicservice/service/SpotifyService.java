package com.spotify.musicservice.service;

import com.spotify.musicservice.dto.SpotifyPlaylist;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

public interface SpotifyService {
    String authorize();
    String callback(String code);
    List<Track> getUserTopSongs(String userId);

    List<PlaylistTrack> getBillBoard100();
    Playlist getBillBoard100Playlist();

    SpotifyPlaylist getBillBoard100PlaylistObj();
}
