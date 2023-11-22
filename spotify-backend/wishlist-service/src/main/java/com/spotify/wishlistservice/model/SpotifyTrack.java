package com.spotify.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SpotifyTrack {

    @JsonProperty("tracks")
    private Tracks tracks;
}
