package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotifyTracksSearch {

    @JsonProperty("tracks")
    private TrackTracks tracks;
}
