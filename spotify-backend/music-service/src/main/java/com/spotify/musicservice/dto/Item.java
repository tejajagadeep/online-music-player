package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty("added_at")
    private String addedAt;

    @JsonProperty("track")
    private Track track;

    // Getters and setters
}