package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AlbumSearch {
    @JsonProperty("items")
    private List<AlbumItemSearch> items;
}
