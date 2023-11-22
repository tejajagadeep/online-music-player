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

}