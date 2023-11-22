package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class ImageDto {
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private Integer width;

    // Getters and setters
}