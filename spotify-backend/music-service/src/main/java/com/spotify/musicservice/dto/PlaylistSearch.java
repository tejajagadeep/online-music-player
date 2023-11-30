package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaylistSearch {

    @JsonProperty("items")
    private List<PlaylistItemsSearch> items;
}
