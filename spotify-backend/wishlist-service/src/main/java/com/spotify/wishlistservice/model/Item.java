package com.spotify.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Item {
    @JsonProperty("added_at")
    private String addedAt;

    @JsonProperty("track")
    private Track track;

    // Getters and setters
}