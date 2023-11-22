package com.spotify.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Tracks {
    @JsonProperty("href")
    private String href;

    @JsonProperty("items")
    private List<Item> items;

}