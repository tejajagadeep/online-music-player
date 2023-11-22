package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SpotifyTrackDto {

    @JsonProperty("tracks")
    private TracksDto tracks;
}
