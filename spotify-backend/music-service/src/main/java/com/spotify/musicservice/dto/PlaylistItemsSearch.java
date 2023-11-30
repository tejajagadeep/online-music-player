package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
