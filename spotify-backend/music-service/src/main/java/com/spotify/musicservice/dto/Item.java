package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Item {

    @JsonProperty("track")
    private Track track;

    // Getters and setters
}