package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import se.michaelthelin.spotify.enums.ModelObjectType;

import java.util.List;

@Data
public class SpotifyPlaylist {

    @JsonProperty("description")
    private String description;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tracks")
    private Tracks tracks;

    @JsonProperty("uri")
    private String uri;

}
