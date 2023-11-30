package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyAlbumSearch {
    @JsonProperty("albums")
    private AlbumSearch albums;
}
