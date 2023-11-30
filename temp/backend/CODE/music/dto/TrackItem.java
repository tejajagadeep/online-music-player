package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrackItem {
    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

}
