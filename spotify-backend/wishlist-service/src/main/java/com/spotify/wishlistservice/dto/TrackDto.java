package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.wishlistservice.model.Album;
import com.spotify.wishlistservice.model.Artist;
import com.spotify.wishlistservice.model.ExternalUrls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {

    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists = new ArrayList<>();

    @JsonProperty("external_urls")
    private ExternalUrls external_urls;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("duration_ms")
    private long durationMs;

    @JsonProperty("type")
    private String type;

}
