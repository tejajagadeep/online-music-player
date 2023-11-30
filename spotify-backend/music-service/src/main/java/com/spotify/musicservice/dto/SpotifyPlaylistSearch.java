package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyPlaylistSearch {
    @JsonProperty("playlists")
    private PlaylistSearch playlistSearch;
}
