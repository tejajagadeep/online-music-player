package com.example.sporifyservicedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackItem {
    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("external_urls")
    private ExternalUrls external_urls;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("type")
    private String type;

}
