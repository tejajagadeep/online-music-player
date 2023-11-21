package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Image {
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private Integer width;

    // Getters and setters
}