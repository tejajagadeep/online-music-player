package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Tracks {
    @JsonProperty("href")
    private String href;

    @JsonProperty("items")
    private List<Item> items;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("next")
    private Object next;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("previous")
    private Object previous;

    @JsonProperty("total")
    private int total;

    // Getters and setters
}