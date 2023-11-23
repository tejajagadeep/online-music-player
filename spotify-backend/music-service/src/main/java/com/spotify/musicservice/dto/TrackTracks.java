package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrackTracks {
    @JsonProperty("href")
    private String href;

    @JsonProperty("items")
    private List<TrackItem> items;


}
