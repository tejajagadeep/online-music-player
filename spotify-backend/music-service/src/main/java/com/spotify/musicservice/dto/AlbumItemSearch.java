package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AlbumItemSearch {

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;
}
