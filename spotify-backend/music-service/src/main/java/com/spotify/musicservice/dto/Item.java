package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Item {
    @JsonProperty("track")
    private Track track;

    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists;
    // Getters and setters
}