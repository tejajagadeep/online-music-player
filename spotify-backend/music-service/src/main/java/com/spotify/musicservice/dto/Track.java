package com.spotify.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    @JsonProperty("album")
    private Albums albums;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("duration_ms")
    private long durationMs;

    @JsonProperty("type")
    private String type;

}
