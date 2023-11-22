package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class TracksDto {
    @JsonProperty("href")
    private String href;

    @JsonProperty("items")
    private List<ItemDto> items;

}