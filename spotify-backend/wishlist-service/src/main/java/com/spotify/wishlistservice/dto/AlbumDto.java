package com.spotify.wishlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.wishlistservice.model.Artist;
import com.spotify.wishlistservice.model.ExternalUrls;
import com.spotify.wishlistservice.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {
    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("artists")
    private List<Artist> artists = new ArrayList<>();

    @JsonProperty("external_urls")
    private ExternalUrls external_urls;


    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("type")
    private String type;

}