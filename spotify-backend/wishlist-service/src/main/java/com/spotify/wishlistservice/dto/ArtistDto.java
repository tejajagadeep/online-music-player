package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    @JsonProperty("external_urls")
    private ExternalUrlsDto externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

    // Getters and setters
}