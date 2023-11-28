package com.example.sporifyservicedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class SpotifyPlaylist {

    @JsonProperty("description")
    private String description;

    @JsonProperty("external_urls")
    private ExternalUrls external_urls;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tracks")
    private Tracks tracks;

}
