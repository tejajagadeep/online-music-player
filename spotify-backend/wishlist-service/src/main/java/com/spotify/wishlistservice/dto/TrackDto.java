package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {

    @JsonProperty("album")
    private AlbumDto album;

    @JsonProperty("artists")
    private List<ArtistDto> artists;

    @JsonProperty("external_urls")
    private ExternalUrlsDto externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

    // Getters and setters
}
