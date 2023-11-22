package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class ItemDto {
    @JsonProperty("track")
    private TrackDto track;

    @JsonProperty("album")
    private AlbumDto album;

    @JsonProperty("artists")
    private List<ArtistDto> artists;
    // Getters and setters
}