package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaylistItemsSearch {

    @JsonProperty("description")
    private String description;
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    @JsonProperty("images")
    private List<Image> images;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;

}
