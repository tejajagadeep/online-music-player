package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class TrackDto {

    @JsonProperty("album")
    private AlbumDto album;

    @JsonProperty("artists")
    private List<ArtistDto> artists;

    @JsonProperty("external_urls")
    private ExternalUrlsDto externalUrls;

    @JsonProperty("href")
    private String href;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

    // Getters and setters
}
