package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpotifyTrack {

    @JsonProperty("tracks")
    private Tracks tracks;
}
